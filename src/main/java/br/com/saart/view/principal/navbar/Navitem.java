package br.com.saart.view.principal.navbar;

import org.kordamp.ikonli.javafx.FontIcon;

public record Navitem(String label, FontIcon icon, boolean grupo, boolean novo, NavItemAction action) {
}
