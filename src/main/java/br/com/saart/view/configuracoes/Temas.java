package br.com.saart.view.configuracoes;

import atlantafx.base.theme.Theme;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Getter
@RequiredArgsConstructor
public enum Temas {

    PrimerLight("Primer Light", getCss("atlantafx.base.theme.PrimerLight")),
    PrimerDark("Primer Dark", getCss("atlantafx.base.theme.PrimerDark")),
    NordLight("Nord Light", getCss("atlantafx.base.theme.NordLight")),
    NordDark("Nord Dark", getCss("atlantafx.base.theme.NordDark")),
    CupertinoLight("Cupertino Light", getCss("atlantafx.base.theme.CupertinoLight")),
    CupertinoDark("Cupertino Dark", getCss("atlantafx.base.theme.CupertinoDark")),
    Dracula("Dracula", getCss("atlantafx.base.theme.Dracula"));

    private final String nome;
    private final String userAgentStylesheet;

    @SneakyThrows
    private static String getCss(String className) {
        return ((Theme) Class.forName(className).getDeclaredConstructor().newInstance()).getUserAgentStylesheet();
    }

    @Override
    public String toString() {
        return nome;
    }
}
