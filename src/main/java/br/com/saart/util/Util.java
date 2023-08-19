package br.com.saart.util;

import br.com.saart.task.exportreport.ReportName;
import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Map;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Util {

    public static final String EOL = System.lineSeparator();
    public static final DateTimeFormatter DATE_BR_FMT = DateTimeFormatter.ofPattern("ddMMuuuu");
    public static final DateTimeFormatter DATE_ISO_FMT = DateTimeFormatter.ofPattern("uuuuMMdd");
    public static final DateTimeFormatter HMS_FMT = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final DateTimeFormatter MY_FMT = DateTimeFormatter.ofPattern("MM/uuuu");
    public static final DateTimeFormatter DMY_FMT = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    public static final DateTimeFormatter YM_FMT = DateTimeFormatter.ofPattern("uuuu-MM");
    public static final DateTimeFormatter DATE_TIME_OPTIONAL_TMZ = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd[[ ]['T']HH:mm[:ss][XXX]]");
    public static final DecimalFormat SPED_NUMBER_FMT;

    static {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator(',');

        SPED_NUMBER_FMT = new DecimalFormat("0.##########", decimalFormatSymbols);
        SPED_NUMBER_FMT.setGroupingUsed(false);
    }

    public static TemporalAccessor parseBest(String temporalStr) {
        return DATE_TIME_OPTIONAL_TMZ.parseBest(temporalStr, OffsetDateTime::from, LocalDateTime::from,
                LocalDate::from);
    }

    public static String toHMS(LocalTime time) {
        return time.format(HMS_FMT);
    }

    public static String toHMS(long millis) {
        return DurationFormatUtils.formatDuration(millis, "HH:mm:ss");
    }

    public static boolean isNumber(String str) {
        try {
            toBigDecimal(str);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    public static boolean isDate(String str) {
        try {
            parseBrDate(str);
            return true;
        } catch (DateTimeParseException | NullPointerException e) {
            return false;
        }
    }

    public static String toAMY(String str) {
        return parseBrDate(str).format(DATE_ISO_FMT);
    }

    public static LocalDate parseBrDate(String str) {
        return StringUtils.isBlank(str) ? null : LocalDate.parse(str.replaceAll("\\D", ""), DATE_BR_FMT);
    }

    public static LocalDate parseIsoDate(String str) {
        return StringUtils.isBlank(str) ? null : LocalDate.parse(str.replaceAll("\\D", ""), DATE_ISO_FMT);
    }

    public static String toMY(String str) {
        return parseBrDate(str).format(MY_FMT);
    }

    public static String toMY(LocalDate date) {
        return date.format(MY_FMT);
    }

    public static String toDMY(LocalDate date) {
        return date.format(DMY_FMT);
    }

    public static String toYM(LocalDate date) {
        return date.format(YM_FMT);
    }

    public static String toMY(Timestamp date) {
        return date.toLocalDateTime().format(MY_FMT);
    }

    public static Boolean toBoolean(String str) {
        return StringUtils.isBlank(str) ? null : StringUtils.equalsAnyIgnoreCase(str, "SIM", "S");
    }

    public static BigDecimal toBigDecimal(double vlr) {
        return toBigDecimal(String.valueOf(vlr));
    }

    public static BigDecimal toBigDecimal(String numericString) {
        return new BigDecimal(StringUtils.firstNonBlank(numericString, "0").replace(",", "."));
    }

    public static String toNumberString(BigDecimal number) {
        return number != null ? SPED_NUMBER_FMT.format(number) : "";
    }

    public static String toNumberString(Double number) {
        return number != null ? SPED_NUMBER_FMT.format(number) : "";
    }

    public static String joinWithPipe(String[] tokens) {
        return StringUtils.join(tokens, "|");
    }

    public static String joinNoSeparator(String[] tokens) {
        return StringUtils.join(tokens);
    }

    public static Object getFirst(Map<String, Object> map, Object defaultValue, String... keys) {
        for (String key : keys) {
            if (map.containsKey(key)) {
                return map.get(key);
            }
        }
        return defaultValue;
    }

    @SneakyThrows
    public static BufferedReader getReader(String path) {
        BufferedInputStream is = new BufferedInputStream(new FileInputStream(path));

        CharsetDetector detector = new CharsetDetector();
        detector.setText(is);
        CharsetMatch charset = detector.detect();
        is.reset();

        return new BufferedReader(new InputStreamReader(is, charset.getName()));

    }

    public static BufferedReader getReader(ClassPathResource resource, Charset charset)
            throws IOException {
        return new BufferedReader(new InputStreamReader(resource.getInputStream(), charset));
    }

    public static BufferedReader getReader(String path, Charset charset) throws IOException {
        return new BufferedReader(
                new InputStreamReader(Files.newInputStream(Paths.get(path)), charset));
    }

    public static BufferedReader getReader(File file, Charset charset) throws IOException {
        return new BufferedReader(new InputStreamReader(Files.newInputStream(file.toPath()), charset));
    }

    public static BufferedWriter getWritter(String path, Charset charset) throws IOException {
        File file = new File(path);
        file.getParentFile().mkdirs();
        return new BufferedWriter(
                new OutputStreamWriter(Files.newOutputStream(file.toPath()), charset));
    }

    public static String[] splitFirstLineAndReset(BufferedReader reader) throws IOException {
        return splitFirstLineAndReset(reader, "\\|");
    }

    public static String[] splitFirstLineAndReset(BufferedReader reader, String separator)
            throws IOException {
        return readFirstLineAndReset(reader).split(separator, -1);
    }

    public static String readFirstLineAndReset(BufferedReader reader) throws IOException {
        reader.mark(8192);
        String firstLine = reader.readLine();
        reader.reset();
        return firstLine;
    }

    public static Optional<Element> getElement(Element root, String tag) {
        return Optional.ofNullable((Element) root.getElementsByTagName(tag).item(0));
    }

    public static Optional<String> getValue(Element root, String tag) {
        return Optional.ofNullable(root.getElementsByTagName(tag).item(0))
                .map(Node::getFirstChild).map(Node::getNodeValue).map(String::toUpperCase);
    }

    public static Optional<Element> searchElement(Element root, String... tags) {
        Optional<Element> node = Optional.ofNullable(root);
        for (String tag : tags) {
            node = node.map(n -> (Element) n.getElementsByTagName(tag).item(0));
        }
        return node;
    }

    public static Optional<String> searchValue(Element root, String... tags) {
        return searchElement(root, tags).map(Node::getFirstChild).map(Node::getNodeValue)
                .map(String::toUpperCase);
    }

    @SneakyThrows
    public static JasperReport loadReportTemplate(ReportName reportName) {
        return (JasperReport) JRLoader.loadObject(
                new ClassPathResource("/reports/" + reportName.name() + ".jasper").getInputStream());
    }

    @SneakyThrows
    public static String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        }
    }

    public static String leftPad(String str, int size, char prefix) {
        str = str.trim();
        return str.length() > size ? StringUtils.truncate(str, size)
                : StringUtils.leftPad(str, size, prefix);
    }

    public static String rightPad(String str, int size, char suffix) {
        str = str.trim();
        return str.length() > size ? StringUtils.truncate(str, size)
                : StringUtils.rightPad(str, size, suffix);
    }

    public static int ceilDiv(int a, int b) {
        return -Math.floorDiv(-a, b);
    }

    public static String normalizeAll(String str) {
        if (str == null) {
            return null;
        }

        return StringUtils.stripAccents(StringUtils.normalizeSpace(str)).toUpperCase();
    }

    public static Year toYear(String s) {
        return Year.of(Integer.parseInt(s));
    }

    public static Short toShort(String s) {
        return StringUtils.isBlank(s) ? null : Short.parseShort(s);
    }

    public static Float toFloat(String s) {
        return StringUtils.isBlank(s) ? null : Float.parseFloat(s);
    }

    public static String[] split(String linha) {
        return linha.split("\\|", -1);
    }

    public static Long byteToMb(Long bytes) {
        return bytes / 1024 / 1024;
    }
}
