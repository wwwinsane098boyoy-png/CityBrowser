// sections/BusinessSection.java
package com.dehradun.sections;

import com.dehradun.components.AnimatedCard;
import com.dehradun.utils.StyleUtils;
import javafx.geometry.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BusinessSection {

    public static void load(VBox container) {
        container.getChildren().add(
            StyleUtils.sectionHeader("💼", "Business & Economy", "Dehradun's economic landscape and opportunities")
        );

        // Key stats
        HBox statsRow = new HBox(16);
        statsRow.getChildren().addAll(
            StyleUtils.statCard("💰", "₹32,000 Cr", "City GDP (Est.)", "#667eea"),
            StyleUtils.statCard("🏢", "5,000+", "Registered Businesses", "#6bcb77"),
            StyleUtils.statCard("💻", "200+", "IT Companies", "#4facfe"),
            StyleUtils.statCard("🎓", "50+", "Educational Institutions", "#ffd93d")
        );
        for (var child : statsRow.getChildren()) {
            HBox.setHgrow(child, Priority.ALWAYS);
        }
        container.getChildren().add(statsRow);

        // Key Industries
        Label indTitle = new Label("🏭  Key Industries");
        indTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        indTitle.setTextFill(Color.WHITE);
        indTitle.setPadding(new Insets(10, 0, 0, 0));
        container.getChildren().add(indTitle);

        FlowPane indGrid = StyleUtils.cardGrid();
        indGrid.getChildren().addAll(
            new AnimatedCard("🎓", "Education & Research",
                "Home to IITs, FRI, UPES, DIT, Doon School, IMA. Major education hub attracting students nationwide.",
                "#667eea", "#764ba2"),
            new AnimatedCard("💻", "Information Technology",
                "Growing IT sector with IT Park at Sahastradhara Road. Companies like HCL, Infosys presence. Startup ecosystem evolving.",
                "#4facfe", "#00f2fe"),
            new AnimatedCard("🏨", "Tourism & Hospitality",
                "Gateway to Mussoorie, Rishikesh, Haridwar. Major hotel chains and adventure tourism operators. Year-round tourism.",
                "#43e97b", "#38f9d7"),
            new AnimatedCard("🏥", "Healthcare",
                "AIIMS Rishikesh, Max Hospital, Doon Hospital. Medical tourism growing. Wellness & Ayurveda centers.",
                "#ff6b6b", "#ffd93d"),
            new AnimatedCard("🛒", "Retail & Real Estate",
                "Pacific Mall, Pal Square, growing commercial zones. Real estate boom in Rajpur Road and Sahastradhara corridor.",
                "#f093fb", "#f5576c"),
            new AnimatedCard("⚡", "Renewable Energy",
                "Solar and hydropower projects in the region. Government pushing for green energy initiatives in Uttarakhand.",
                "#ffd93d", "#ff9a00")
        );
        container.getChildren().add(indGrid);

        container.getChildren().add(StyleUtils.separator());

        // Top Employers
        VBox employerBox = StyleUtils.glassCard("", 0);
        employerBox.setMaxWidth(Double.MAX_VALUE);
        employerBox.setSpacing(10);

        Label empTitle = new Label("🏢  Major Employers & Institutions");
        empTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        empTitle.setTextFill(Color.WHITE);
        employerBox.getChildren().add(empTitle);

        String[][] employers = {
            {"🎖", "Indian Military Academy (IMA)", "Defense Training", "Est. 1932"},
            {"🌲", "Forest Research Institute (FRI)", "Research & Education", "Est. 1906"},
            {"🛢", "Indian Institute of Petroleum", "Oil & Gas Research", "Est. 1960"},
            {"🏫", "UPES", "Private University", "10,000+ Students"},
            {"📡", "ONGC", "Oil & Natural Gas Corp", "Major Employer"},
            {"🏛", "Survey of India", "National Mapping Agency", "HQ in Dehradun"},
            {"🎓", "DIT University", "Private University", "Engineering & Management"},
            {"🏨", "Uttarakhand Tourism", "State Tourism Body", "HQ in Dehradun"}
        };

        for (String[] emp : employers) {
            HBox row = new HBox(12);
            row.setAlignment(Pos.CENTER_LEFT);
            row.setPadding(new Insets(10, 14, 10, 14));
            row.setStyle(
                "-fx-background-color: rgba(255,255,255,0.03);" +
                "-fx-background-radius: 10;"
            );

            Label icon = new Label(emp[0]);
            icon.setFont(Font.font(18));

            VBox info = new VBox(2);
            Label name = new Label(emp[1]);
            name.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 13));
            name.setTextFill(Color.WHITE);
            Label desc = new Label(emp[2]);
            desc.setFont(Font.font("Segoe UI", 10.5));
            desc.setTextFill(Color.web("#ffffff", 0.45));
            info.getChildren().addAll(name, desc);
            HBox.setHgrow(info, Priority.ALWAYS);

            Label extra = new Label(emp[3]);
            extra.setFont(Font.font("Segoe UI", 10));
            extra.setTextFill(Color.web("#667eea"));

            row.getChildren().addAll(icon, info, extra);
            employerBox.getChildren().add(row);
        }

        container.getChildren().add(employerBox);

        // Startup ecosystem
        VBox startupBox = StyleUtils.glassCard("", 0);
        startupBox.setMaxWidth(Double.MAX_VALUE);
        Label startupTitle = new Label("🚀  Startup Ecosystem");
        startupTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        startupTitle.setTextFill(Color.WHITE);

        Label startupText = new Label(
            "Dehradun's startup ecosystem is rapidly growing with support from:\n\n" +
            "• Uttarakhand Startup Policy — Offers subsidies, mentorship, and incubation support\n" +
            "• UPES Innovation Hub — University-backed incubator for tech startups\n" +
            "• Coworking Spaces — Multiple spaces on Rajpur Road and EC Road\n" +
            "• Focus Sectors — EdTech, Tourism Tech, AgriTech, Wellness & Health Tech\n" +
            "• Funding — Angel investor network growing; proximity to Delhi-NCR helps\n\n" +
            "The city offers lower operating costs compared to metros, a good talent pool from " +
            "local universities, and an excellent quality of life."
        );
        startupText.setFont(Font.font("Segoe UI", 12));
        startupText.setTextFill(Color.web("#ffffff", 0.6));
        startupText.setWrapText(true);
        startupText.setLineSpacing(3);
        startupBox.getChildren().addAll(startupTitle, startupText);
        container.getChildren().add(startupBox);
    }
}