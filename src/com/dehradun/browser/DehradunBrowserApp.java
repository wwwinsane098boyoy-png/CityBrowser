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
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;

public class DehradunBrowserApp extends Application {

    private StackPane rootStack;
    private BorderPane mainLayout;
    private ScrollPane contentScroll;
    private VBox contentArea;
    private String currentSection = "HOME";

    // Color Palette
    public static final Color PRIMARY = Color.web("#667eea");
    public static final Color SECONDARY = Color.web("#764ba2");
    public static final Color ACCENT = Color.web("#f093fb");
    public static final Color DARK_BG = Color.web("#0f0c29");
    public static final Color CARD_BG = Color.web("rgba(255,255,255,0.08)");

    private double xOffset = 0;
    private double yOffset = 0;
@Override
public void start(Stage primaryStage) {

    primaryStage.initStyle(StageStyle.UNDECORATED);

    rootStack = new StackPane();
    rootStack.setStyle("-fx-background-color: transparent;");

    // Animated background
    Rectangle bgRect = createAnimatedBackground();
    rootStack.getChildren().add(bgRect);

    // Particles
    Pane particlePane = createParticles();
    rootStack.getChildren().add(particlePane);

    mainLayout = new BorderPane();

    HBox titleBar = createTitleBar(primaryStage);
    mainLayout.setTop(titleBar);

    VBox sidebar = createSidebar();
    mainLayout.setLeft(sidebar);

    contentArea = new VBox(20);
    contentArea.setPadding(new Insets(25));
    contentArea.setAlignment(Pos.TOP_LEFT);

    contentScroll = new ScrollPane(contentArea);
    contentScroll.setFitToWidth(true);
    contentScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

    mainLayout.setCenter(contentScroll);

    rootStack.getChildren().add(mainLayout);

    Scene scene = new Scene(rootStack, 1300, 800);

    // Dynamic clip
    Rectangle clip = new Rectangle();
    clip.setArcWidth(20);
    clip.setArcHeight(20);

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

   private Rectangle createAnimatedBackground() {

    Rectangle rect = new Rectangle();

    rect.widthProperty().bind(rootStack.widthProperty());
    rect.heightProperty().bind(rootStack.heightProperty());

    LinearGradient gradient = new LinearGradient(
        0, 0, 1, 1,
        true,
        CycleMethod.NO_CYCLE,
        new Stop(0, Color.web("#0f0c29")),
        new Stop(0.5, Color.web("#302b63")),
        new Stop(1, Color.web("#24243e"))
    );

    rect.setFill(gradient);

    return rect;
}

   private Pane createParticles() {

    Pane pane = new Pane();

    pane.setMouseTransparent(true);

    pane.prefWidthProperty().bind(rootStack.widthProperty());
    pane.prefHeightProperty().bind(rootStack.heightProperty());

    for (int i = 0; i < 30; i++) {

        Circle particle = new Circle(Math.random() * 3 + 1);

        particle.setFill(
            Color.web("#ffffff", 0.1 + Math.random() * 0.15)
        );

        particle.setCenterX(Math.random() * 1300);
        particle.setCenterY(Math.random() * 800);

        TranslateTransition tt = new TranslateTransition(
            Duration.seconds(8 + Math.random() * 12),
            particle
        );

        tt.setByY(-(200 + Math.random() * 400));
        tt.setByX((Math.random() - 0.5) * 100);

        tt.setCycleCount(Animation.INDEFINITE);
        tt.setAutoReverse(true);

        tt.play();

        pane.getChildren().add(particle);
    }

    return pane;
}
    private HBox createTitleBar(Stage stage) {
        HBox titleBar = new HBox();
        titleBar.setPadding(new Insets(12, 20, 12, 20));
        titleBar.setAlignment(Pos.CENTER_LEFT);
        titleBar.setStyle(
            "-fx-background-color: rgba(255,255,255,0.05);" +
            "-fx-border-color: rgba(255,255,255,0.08);" +
            "-fx-border-width: 0 0 1 0;"
        );

        // App icon/logo
        Label logo = new Label("🏔");
        logo.setFont(Font.font("Segoe UI Emoji", 22));

        Label title = new Label("  DEHRADUN");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        title.setTextFill(Color.WHITE);

        Label subtitle = new Label("  City Explorer");
        subtitle.setFont(Font.font("Segoe UI", FontWeight.LIGHT, 12));
        subtitle.setTextFill(Color.web("#ffffff", 0.6));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Search bar
        TextField searchField = createSearchField();

        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);

        // Window controls
        HBox windowControls = createWindowControls(stage);

        titleBar.getChildren().addAll(logo, title, subtitle, spacer, searchField, spacer2, windowControls);

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

    private TextField createSearchField() {
        TextField field = new TextField();
        field.setPromptText("🔍  Search Dehradun...");
        field.setPrefWidth(300);
        field.setPrefHeight(35);
        field.setStyle(
            "-fx-background-color: rgba(255,255,255,0.08);" +
            "-fx-text-fill: white;" +
            "-fx-prompt-text-fill: rgba(255,255,255,0.4);" +
            "-fx-background-radius: 20;" +
            "-fx-border-radius: 20;" +
            "-fx-border-color: rgba(255,255,255,0.12);" +
            "-fx-border-width: 1;" +
            "-fx-padding: 8 16;" +
            "-fx-font-size: 13;"
        );
        field.focusedProperty().addListener((obs, o, n) -> {
            if (n) {
                field.setStyle(field.getStyle().replace(
                    "rgba(255,255,255,0.08)", "rgba(255,255,255,0.12)"));
            } else {
                field.setStyle(field.getStyle().replace(
                    "rgba(255,255,255,0.12)", "rgba(255,255,255,0.08)"));
            }
        });
        return field;
    }

    private HBox createWindowControls(Stage stage) {
        HBox controls = new HBox(8);
        controls.setAlignment(Pos.CENTER);

        Button minimize = createControlButton("—", "#ffd93d");
        minimize.setOnAction(e -> stage.setIconified(true));

        Button maximize = createControlButton("□", "#6bcb77");
        maximize.setOnAction(e -> {

    if (stage.isMaximized()) {

        stage.setMaximized(false);

    } else {

        Rectangle2D bounds =
            Screen.getPrimary().getVisualBounds();

        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());

        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
    }
});
        Button close = createControlButton("✕", "#ff6b6b");
        close.setOnAction(e -> {
            FadeTransition ft = new FadeTransition(Duration.millis(300), rootStack);
            ft.setToValue(0);
            ft.setOnFinished(ev -> stage.close());
            ft.play();
        });

        controls.getChildren().addAll(minimize, maximize, close);
        return controls;
    }

    private Button createControlButton(String text, String color) {
        Button btn = new Button(text);
        btn.setPrefSize(32, 32);
        btn.setStyle(
            "-fx-background-color: " + color + "33;" +
            "-fx-text-fill: " + color + ";" +
            "-fx-background-radius: 50;" +
            "-fx-border-radius: 50;" +
            "-fx-font-size: 11;" +
            "-fx-cursor: hand;"
        );
        btn.setOnMouseEntered(e -> btn.setStyle(
            "-fx-background-color: " + color + "66;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 50;" +
            "-fx-border-radius: 50;" +
            "-fx-font-size: 11;" +
            "-fx-cursor: hand;"
        ));
        btn.setOnMouseExited(e -> btn.setStyle(
            "-fx-background-color: " + color + "33;" +
            "-fx-text-fill: " + color + ";" +
            "-fx-background-radius: 50;" +
            "-fx-border-radius: 50;" +
            "-fx-font-size: 11;" +
            "-fx-cursor: hand;"
        ));
        return btn;
    }

    private VBox createSidebar() {
        VBox sidebar = new VBox(4);
        sidebar.setPadding(new Insets(20, 10, 20, 10));
        sidebar.setPrefWidth(220);
        sidebar.setStyle(
            "-fx-background-color: rgba(255,255,255,0.03);" +
            "-fx-border-color: rgba(255,255,255,0.06);" +
            "-fx-border-width: 0 1 0 0;"
        );
        sidebar.setAlignment(Pos.TOP_CENTER);

        String[][] menuItems = {
            {"🏠", "HOME", "Overview"},
            {"📍", "PLACES", "Places to Visit"},
            {"🍜", "FOOD", "Food & Cuisine"},
            {"🌤", "WEATHER", "Weather Info"},
            {"📰", "NEWS", "Local News"},
            {"💼", "BUSINESS", "Business Hub"},
            {"📋", "ESSENTIALS", "Travel Essentials"}
        };

        Label navLabel = new Label("NAVIGATION");
        navLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 10));
        navLabel.setTextFill(Color.web("#ffffff", 0.3));
        navLabel.setPadding(new Insets(0, 0, 10, 10));

        sidebar.getChildren().add(navLabel);

        for (String[] item : menuItems) {
            Button navBtn = createNavButton(item[0], item[2], item[1]);
            sidebar.getChildren().add(navBtn);
        }

        // Bottom info
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        sidebar.getChildren().add(spacer);

        VBox infoBox = new VBox(5);
        infoBox.setPadding(new Insets(15));
        infoBox.setStyle(
            "-fx-background-color: rgba(102,126,234,0.15);" +
            "-fx-background-radius: 12;" +
            "-fx-border-radius: 12;" +
            "-fx-border-color: rgba(102,126,234,0.2);" +
            "-fx-border-width: 1;"
        );
        Label infoTitle = new Label("📌 Quick Fact");
        infoTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 11));
        infoTitle.setTextFill(Color.web("#667eea"));
        Label infoText = new Label("Dehradun is the capital of Uttarakhand, nestled in the Doon Valley.");
        infoText.setFont(Font.font("Segoe UI", 10));
        infoText.setTextFill(Color.web("#ffffff", 0.6));
        infoText.setWrapText(true);
        infoBox.getChildren().addAll(infoTitle, infoText);
        sidebar.getChildren().add(infoBox);

        return sidebar;
    }

    private Button createNavButton(String icon, String text, String sectionId) {
        Button btn = new Button(icon + "  " + text);
        btn.setPrefWidth(200);
        btn.setPrefHeight(42);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setFont(Font.font("Segoe UI", 13));

        String defaultStyle =
            "-fx-background-color: transparent;" +
            "-fx-text-fill: rgba(255,255,255,0.6);" +
            "-fx-background-radius: 10;" +
            "-fx-border-radius: 10;" +
            "-fx-padding: 10 16;" +
            "-fx-cursor: hand;";

        String hoverStyle =
            "-fx-background-color: rgba(255,255,255,0.08);" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 10;" +
            "-fx-border-radius: 10;" +
            "-fx-padding: 10 16;" +
            "-fx-cursor: hand;";

        String activeStyle =
            "-fx-background-color: linear-gradient(to right, rgba(102,126,234,0.3), rgba(118,75,162,0.2));" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 10;" +
            "-fx-border-radius: 10;" +
            "-fx-border-color: rgba(102,126,234,0.4);" +
            "-fx-border-width: 1;" +
            "-fx-padding: 10 16;" +
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
            // Update all nav buttons
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

    public void loadSection(String section) {
        contentArea.getChildren().clear();
        contentArea.setOpacity(0);

        switch (section) {
            case "HOME": HomeSection.load(contentArea); break;
            case "PLACES": PlacesSection.load(contentArea); break;
            case "FOOD": FoodSection.load(contentArea); break;
            case "WEATHER": WeatherSection.load(contentArea); break;
            case "NEWS": NewsSection.load(contentArea); break;
            case "BUSINESS": BusinessSection.load(contentArea); break;
            case "ESSENTIALS": EssentialsSection.load(contentArea); break;
        }

        // Fade in animation
        FadeTransition ft = new FadeTransition(Duration.millis(400), contentArea);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

        // Slide up each child
        for (int i = 0; i < contentArea.getChildren().size(); i++) {
            var node = contentArea.getChildren().get(i);
            node.setTranslateY(30);
            node.setOpacity(0);
            TranslateTransition tt = new TranslateTransition(Duration.millis(400), node);
            tt.setDelay(Duration.millis(i * 80));
            tt.setToY(0);
            tt.setInterpolator(Interpolator.EASE_OUT);
            tt.play();
            FadeTransition ft2 = new FadeTransition(Duration.millis(400), node);
            ft2.setDelay(Duration.millis(i * 80));
            ft2.setToValue(1);
            ft2.play();
        }

        contentScroll.setVvalue(0);
    }

    private void playEntranceAnimation() {
        rootStack.setScaleX(0.95);
        rootStack.setScaleY(0.95);
        rootStack.setOpacity(0);

        ScaleTransition st = new ScaleTransition(Duration.millis(500), rootStack);
        st.setToX(1);
        st.setToY(1);
        st.setInterpolator(Interpolator.EASE_OUT);
        st.play();

        FadeTransition ft = new FadeTransition(Duration.millis(500), rootStack);
        ft.setToValue(1);
        ft.play();
    }

    private String getInlineCSS() {
        // Create temporary CSS file or use inline
        String css = getClass().getResource("/styles.css") != null ?
            getClass().getResource("/styles.css").toExternalForm() : "";
        return css;
    }

    public static void main(String[] args) {
        launch(args);
    }
}