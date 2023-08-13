package br.com.saart.util;

import br.com.saart.task.exportreport.ReportFormat;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

@Component
public class UserPreferences {

    @Autowired
    private Preferences prefs;

    public String get(Preference preference) {
        return prefs.get(preference.key, preference.defaultValue);
    }

    public void set(Preference preference, String value) {
        prefs.put(preference.key, ObjectUtils.firstNonNull(value, ""));
    }

    public void reset() {
        try {
            prefs.clear();
        } catch (BackingStoreException | IllegalStateException e) {
            Arrays.stream(Preference.values()).map(preference -> preference.key).forEach(prefs::remove);
        }
    }

    @AllArgsConstructor
    public enum Preference {

        TEMA("tema", "PrimerLight"),

        IMP_DIRF_INPUT("impSpedDir", SystemUtils.USER_DIR + "\\SPED Originais"),
        IMP_DIRF_INPUT_CHARSET("impSpedCharset", StandardCharsets.ISO_8859_1.name()),

        REPORT_NAME("reportName", null),
        REPORT_OUTPUT("reportOutput", SystemUtils.USER_DIR + "\\Relatórios"),
        REPORT_FORMAT("reportFormat", ReportFormat.PDF.name());

        private final String key;
        private final String defaultValue;
    }

}
