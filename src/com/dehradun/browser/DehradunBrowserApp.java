// DehradunBrowserApp.java
package com.dehradun.browser;

import com.dehradun.components.*;
import com.dehradun.sections.*;
import com.dehradun.utils.StyleUtils;
import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.geometry.Rectangle2D;

public class DehradunBrowserApp extends Application {

    private StackPane rootStack;
    private BorderPane mainLayout;
    private ScrollPane contentScroll;
    private VBox contentArea;
    private String currentSection = "HOME";

    // ── LeetCode-inspired Color Palette ───────────────────────────────────────
    public static final Color PRIMARY      = Color.web("#FFA116");   // LeetCode amber/orange
    public static final Color SECONDARY    = Color.web("#FFB800");   // Gold accent
    public static final Color ACCENT       = Color.web("#FFA116");   // Amber accent
    public static final Color DARK_BG      = Color.web("#1A1A1A");   // Deep black background
    public static final Color DARKER_BG    = Color.web("#141414");   // Even darker
    public static final Color CARD_BG      = Color.web("#282828");   // Card / surface
    public static final Color SIDEBAR_BG   = Color.web("#1E1E1E");   // Sidebar
    public static final Color BORDER_COLOR = Color.web("#333333");   // Subtle borders
    public static final Color TEXT_PRIMARY  = Color.web("#EFEFEF");   // Bright white text
    public static final Color TEXT_SECONDARY= Color.web("#A0A0A0");  // Muted grey text
    public static final Color TEXT_MUTED    = Color.web("#5C5C5C");  // Very muted text
    public static final Color HOVER_BG     = Color.web("#303030");   // Hover surface
    public static final Color ACTIVE_BG    = Color.web("#2A2A2A");   // Active / selected

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) {

        primaryStage.initStyle(StageStyle.UNDECORATED);

        rootStack = new StackPane();
        rootStack.setStyle("-fx-background-color: #1A1A1A;");

        // ── Solid dark background ─────────────────────────────────────────────
        Rectangle bgRect = createBackground();
        rootStack.getChildren().add(bgRect);

        // ── Main layout ───────────────────────────────────────────────────────
        mainLayout = new BorderPane();

        HBox titleBar = createTitleBar(primaryStage);
        mainLayout.setTop(titleBar);

        VBox sidebar = createSidebar();
        mainLayout.setLeft(sidebar);

        contentArea = new VBox(20);
        contentArea.setPadding(new Insets(30));
        contentArea.setAlignment(Pos.TOP_LEFT);
        contentArea.setStyle("-fx-background-color: transparent;");

        contentScroll = new ScrollPane(contentArea);
        contentScroll.setFitToWidth(true);
        contentScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        contentScroll.setStyle(
            "-fx-background-color: #1A1A1A;" +
            "-fx-background: #1A1A1A;" +
            "-fx-border-color: transparent;"
        );

        mainLayout.setCenter(contentScroll);

        rootStack.getChildren().add(mainLayout);

        // ── Scene ─────────────────────────────────────────────────────────────
        Scene scene = new Scene(rootStack, 1300, 800);

        Rectangle clip = new Rectangle();
        clip.setArcWidth(12);
        clip.setArcHeight(12);
        clip.widthProperty().bind(scene.widthProperty());
        clip.heightProperty().bind(scene.heightProperty());
        rootStack.setClip(clip);

        scene.setFill(Color.TRANSPARENT);

        String css = getInlineCSS();
        if (css != null && !css.isEmpty()) {
            scene.getStylesheets().add(css);
        }

        primaryStage.setScene(scene);
        primaryStage.setTitle("Dehradun City Browser");
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(700);
        primaryStage.show();

        loadSection("HOME");
        playEntranceAnimation();
    }

    // ── Background ────────────────────────────────────────────────────────────

    private Rectangle createBackground() {

        Rectangle rect = new Rectangle();
        rect.widthProperty().bind(rootStack.widthProperty());
        rect.heightProperty().bind(rootStack.heightProperty());

        // Clean dark gradient — subtle, not flashy
        LinearGradient gradient = new LinearGradient(
            0, 0, 0, 1,
            true,
            CycleMethod.NO_CYCLE,
            new Stop(0,   Color.web("#1A1A1A")),
            new Stop(0.5, Color.web("#1C1C1C")),
            new Stop(1,   Color.web("#181818"))
        );
        rect.setFill(gradient);
        return rect;
    }

    // ── Title bar ─────────────────────────────────────────────────────────────

    private HBox createTitleBar(Stage stage) {

        HBox titleBar = new HBox();
        titleBar.setPadding(new Insets(10, 20, 10, 20));
        titleBar.setAlignment(Pos.CENTER_LEFT);
        titleBar.setStyle(
            "-fx-background-color: #141414;" +
            "-fx-border-color: #2A2A2A;" +
            "-fx-border-width: 0 0 1 0;"
        );

        // ── App branding ──────────────────────────────────────────────────────
        Label logo = new Label("🏔");
        logo.setFont(Font.font("Segoe UI Emoji", 20));

        Label title = new Label("  DEHRADUN");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
        title.setTextFill(Color.web("#EFEFEF"));

        Label subtitle = new Label("  City Explorer");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.LIGHT, 11));
        subtitle.setTextFill(Color.web("#5C5C5C"));

        // ── Spacer ────────────────────────────────────────────────────────────
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // ── Window controls ───────────────────────────────────────────────────
        HBox windowControls = createWindowControls(stage);

        titleBar.getChildren().addAll(logo, title, subtitle, spacer, windowControls);

        // Draggable
        titleBar.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        titleBar.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() - xOffset);
            stage.setY(e.getScreenY() - yOffset);
        });

        return titleBar;
    }

    // ── Window controls — grey/white elegant tones ────────────────────────────

    private HBox createWindowControls(Stage stage) {

        HBox controls = new HBox(6);
        controls.setAlignment(Pos.CENTER);

        // Minimize — muted grey
        Button minimize = createControlButton("—", "#808080", "#B0B0B0");
        minimize.setOnAction(e -> stage.setIconified(true));

        // Maximize — slightly brighter grey
        Button maximize = createControlButton("□", "#808080", "#B0B0B0");
        maximize.setOnAction(e -> {
            if (stage.isMaximized()) {
                stage.setMaximized(false);
            } else {
                Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
                stage.setX(bounds.getMinX());
                stage.setY(bounds.getMinY());
                stage.setWidth(bounds.getWidth());
                stage.setHeight(bounds.getHeight());
            }
        });

        // Close — subtle warm tone (not aggressive red)
        Button close = createControlButton("✕", "#808080", "#E05050");
        close.setOnAction(e -> {
            FadeTransition ft = new FadeTransition(Duration.millis(250), rootStack);
            ft.setToValue(0);
            ft.setOnFinished(ev -> stage.close());
            ft.play();
        });

        controls.getChildren().addAll(minimize, maximize, close);
        return controls;
    }

    /**
     * Elegant minimal control button.
     *
     * @param text       symbol displayed
     * @param color      default icon colour
     * @param hoverColor icon colour on hover
     */
    private Button createControlButton(String text, String color, String hoverColor) {

        Button btn = new Button(text);
        btn.setPrefSize(30, 30);

        String defaultStyle =
            "-fx-background-color: transparent;" +
            "-fx-text-fill: " + color + ";" +
            "-fx-background-radius: 6;" +
            "-fx-border-radius: 6;" +
            "-fx-font-size: 11;" +
            "-fx-cursor: hand;";

        String hoverStyle =
            "-fx-background-color: #2A2A2A;" +
            "-fx-text-fill: " + hoverColor + ";" +
            "-fx-background-radius: 6;" +
            "-fx-border-radius: 6;" +
            "-fx-font-size: 11;" +
            "-fx-cursor: hand;";

        btn.setStyle(defaultStyle);
        btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
        btn.setOnMouseExited (e -> btn.setStyle(defaultStyle));

        return btn;
    }

    // ── Sidebar ───────────────────────────────────────────────────────────────

    private VBox createSidebar() {

        VBox sidebar = new VBox(2);
        sidebar.setPadding(new Insets(20, 8, 20, 8));
        sidebar.setPrefWidth(230);
        sidebar.setStyle(
            "-fx-background-color: #1E1E1E;" +
            "-fx-border-color: #2A2A2A;" +
            "-fx-border-width: 0 1 0 0;"
        );
        sidebar.setAlignment(Pos.TOP_CENTER);

        // ── Navigation label ──────────────────────────────────────────────────
        Label navLabel = new Label("NAVIGATION");
        navLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 10));
        navLabel.setTextFill(Color.web("#5C5C5C"));
        navLabel.setPadding(new Insets(0, 0, 12, 12));
        sidebar.getChildren().add(navLabel);

        // ── Menu items ────────────────────────────────────────────────────────
        String[][] menuItems = {
            {"🏠", "HOME",       "Overview"},
            {"📍", "PLACES",     "Places to Visit"},
            {"🍜", "FOOD",       "Food & Cuisine"},
            {"🌤", "WEATHER",    "Weather Info"},
            {"📰", "NEWS",       "Local News"},
            {"💼", "BUSINESS",   "Business Hub"},
            {"📋", "ESSENTIALS", "Travel Essentials"}
        };

        for (String[] item : menuItems) {
            Button navBtn = createNavButton(item[0], item[2], item[1]);
            sidebar.getChildren().add(navBtn);
        }

        // ── Divider ──────────────────────────────────────────────────────────
        Region dividerSpacer = new Region();
        VBox.setVgrow(dividerSpacer, Priority.ALWAYS);
        sidebar.getChildren().add(dividerSpacer);

        Separator sep = new Separator();
        sep.setStyle("-fx-background-color: #333333;");
        sep.setPadding(new Insets(8, 4, 8, 4));
        sidebar.getChildren().add(sep);

        // ── Quick fact box ────────────────────────────────────────────────────
        VBox infoBox = new VBox(6);
        infoBox.setPadding(new Insets(14));
        infoBox.setStyle(
            "-fx-background-color: #252525;" +
            "-fx-background-radius: 10;" +
            "-fx-border-radius: 10;" +
            "-fx-border-color: #333333;" +
            "-fx-border-width: 1;"
        );

        Label infoTitle = new Label("📌 Quick Fact");
        infoTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 11));
        infoTitle.setTextFill(Color.web("#FFA116"));

        Label infoText = new Label(
            "Dehradun is the capital of Uttarakhand, nestled in the Doon Valley."
        );
        infoText.setFont(Font.font("Segoe UI", 10));
        infoText.setTextFill(Color.web("#808080"));
        infoText.setWrapText(true);

        infoBox.getChildren().addAll(infoTitle, infoText);
        sidebar.getChildren().add(infoBox);

        return sidebar;
    }

    // ── Navigation button ─────────────────────────────────────────────────────

    private Button createNavButton(String icon, String text, String sectionId) {

        Button btn = new Button(icon + "  " + text);
        btn.setPrefWidth(210);
        btn.setPrefHeight(40);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setFont(Font.font("Segoe UI", 13));

        String defaultStyle =
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #A0A0A0;" +
            "-fx-background-radius: 8;" +
            "-fx-border-radius: 8;" +
            "-fx-padding: 8 14;" +
            "-fx-cursor: hand;";

        String hoverStyle =
            "-fx-background-color: #282828;" +
            "-fx-text-fill: #E0E0E0;" +
            "-fx-background-radius: 8;" +
            "-fx-border-radius: 8;" +
            "-fx-padding: 8 14;" +
            "-fx-cursor: hand;";

        String activeStyle =
            "-fx-background-color: #2A2A2A;" +
            "-fx-text-fill: #FFFFFF;" +
            "-fx-background-radius: 8;" +
            "-fx-border-radius: 8;" +
            "-fx-border-color: #FFA116;" +
            "-fx-border-width: 0 0 0 3;" +
            "-fx-padding: 8 14;" +
            "-fx-cursor: hand;";

        btn.setStyle(defaultStyle);

        btn.setOnMouseEntered(e -> {
            if (!currentSection.equals(sectionId)) btn.setStyle(hoverStyle);
        });
        btn.setOnMouseExited(e -> {
            if (!currentSection.equals(sectionId)) btn.setStyle(defaultStyle);
        });
        btn.setOnAction(e -> {
            currentSection = sectionId;
            loadSection(sectionId);

            // Reset all siblings, highlight this one
            VBox parent = (VBox) btn.getParent();
            for (var node : parent.getChildren()) {
                if (node instanceof Button) {
                    ((Button) node).setStyle(defaultStyle);
                }
            }
            btn.setStyle(activeStyle);
        });

        return btn;
    }

    // ── Section loader ────────────────────────────────────────────────────────

    public void loadSection(String section) {

        contentArea.getChildren().clear();
        contentArea.setOpacity(0);

        switch (section) {
            case "HOME"       -> HomeSection.load(contentArea);
            case "PLACES"     -> PlacesSection.load(contentArea);
            case "FOOD"       -> FoodSection.load(contentArea);
            case "WEATHER"    -> WeatherSection.load(contentArea);
            case "NEWS"       -> NewsSection.load(contentArea);
            case "BUSINESS"   -> BusinessSection.load(contentArea);
            case "ESSENTIALS" -> EssentialsSection.load(contentArea);
        }

        // Fade in
        FadeTransition ft = new FadeTransition(Duration.millis(350), contentArea);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

        // Staggered slide-up for children
        for (int i = 0; i < contentArea.getChildren().size(); i++) {
            var node = contentArea.getChildren().get(i);
            node.setTranslateY(20);
            node.setOpacity(0);

            TranslateTransition tt = new TranslateTransition(Duration.millis(350), node);
            tt.setDelay(Duration.millis(i * 60L));
            tt.setToY(0);
            tt.setInterpolator(Interpolator.EASE_OUT);
            tt.play();

            FadeTransition ft2 = new FadeTransition(Duration.millis(350), node);
            ft2.setDelay(Duration.millis(i * 60L));
            ft2.setToValue(1);
            ft2.play();
        }

        contentScroll.setVvalue(0);
    }

    // ── Entrance animation ────────────────────────────────────────────────────

    private void playEntranceAnimation() {

        rootStack.setScaleX(0.97);
        rootStack.setScaleY(0.97);
        rootStack.setOpacity(0);

        ScaleTransition st = new ScaleTransition(Duration.millis(400), rootStack);
        st.setToX(1);
        st.setToY(1);
        st.setInterpolator(Interpolator.EASE_OUT);
        st.play();

        FadeTransition ft = new FadeTransition(Duration.millis(400), rootStack);
        ft.setToValue(1);
        ft.play();
    }

    // ── CSS helper ────────────────────────────────────────────────────────────

    private String getInlineCSS() {
        var res = getClass().getResource("/styles.css");
        return (res != null) ? res.toExternalForm() : "";
    }

    // ── Entry point ───────────────────────────────────────────────────────────

    public static void main(String[] args) {
        launch(args);
    }
}
