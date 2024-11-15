package br.com.saart.task.exportreport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class TaskParam {

    private final String param;
    private final String description;
    private final ParamClass paramClass;
    @Setter
    private boolean required = true;
    @Setter
    private Object defaultValue = null;

    @Getter
    @AllArgsConstructor
    public enum ParamClass {

        STRING(false),
        STRING_ARRAY(false),
        INT_AS_STRING_ARRAY(false),
        DATE_AS_TIMESTAMP(false),
        BOOLEAN(false),
        INT(false),
        INT_ARRAY(false),
        INT_AS_BIG_DECIMAL_ARRAY(false),
        DOUBLE_COMPARISON(true);

        private final boolean comparison;
    }
}
