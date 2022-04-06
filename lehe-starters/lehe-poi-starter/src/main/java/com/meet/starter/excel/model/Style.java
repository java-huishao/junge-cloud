package com.lehe.starter.excel.model;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * 样式,可调用builder()方法逐步构建此对象
 *
 * @author yudong
 * 2019/6/9
 */

public class Style {

    private final String fontName;
    private final int fontColor;
    private final boolean fontBold;
    private final int fontUnderline;
    private final boolean fontItalic;
    private final int fontHeightInPoints;

    private final int foregroundColor;
    private final int backgroundColor;

    private final boolean needLeftBorder;
    private final boolean needRightBorder;
    private final boolean needTopBorder;
    private final boolean needBottomBorder;
    private final int borderColor;
    private final BorderStyle borderWeight;

    private final boolean autoLineFeed;

    private final int alignment;
    private final int verticalAlignment;

    private final String datePattern;
    private final String decimalPattern;

    Style(String fontName, int fontColor, boolean fontBold, int fontUnderline, boolean fontItalic, int fontHeightInPoints,
          int foregroundColor, int backgroundColor, boolean needLeftBorder, boolean needRightBorder, boolean needTopBorder,
          boolean needBottomBorder, int borderColor, BorderStyle borderWeight, boolean autoLineFeed, int alignment,
          int verticalAlignment, String datePattern, String decimalPattern) {
        this.fontName = fontName;
        this.fontColor = fontColor;
        this.fontBold = fontBold;
        this.fontUnderline = fontUnderline;
        this.fontItalic = fontItalic;
        this.fontHeightInPoints = fontHeightInPoints;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        this.needLeftBorder = needLeftBorder;
        this.needRightBorder = needRightBorder;
        this.needTopBorder = needTopBorder;
        this.needBottomBorder = needBottomBorder;
        this.borderColor = borderColor;
        this.borderWeight = borderWeight;
        this.autoLineFeed = autoLineFeed;
        this.alignment = alignment;
        this.verticalAlignment = verticalAlignment;
        this.datePattern = datePattern;
        this.decimalPattern = decimalPattern;
    }

    public static StyleBuilder builder() {
        return new StyleBuilder();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getFontName() {
        return fontName;
    }

    public int getFontColor() {
        return fontColor;
    }

    public boolean getFontBold() {
        return fontBold;
    }

    public int getFontUnderline() {
        return fontUnderline;
    }

    public boolean isFontItalic() {
        return fontItalic;
    }

    public int getFontHeightInPoints() {
        return fontHeightInPoints;
    }

    public int getForegroundColor() {
        return foregroundColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public boolean isNeedLeftBorder() {
        return needLeftBorder;
    }

    public boolean isNeedRightBorder() {
        return needRightBorder;
    }

    public boolean isNeedTopBorder() {
        return needTopBorder;
    }

    public boolean isNeedBottomBorder() {
        return needBottomBorder;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public BorderStyle getBorderWeight() {
        return borderWeight;
    }

    public boolean isAutoLineFeed() {
        return autoLineFeed;
    }

    public int getAlignment() {
        return alignment;
    }

    public int getVerticalAlignment() {
        return verticalAlignment;
    }

    public String getDatePattern() {
        return datePattern;
    }

    public String getDecimalPattern() {
        return decimalPattern;
    }

    /**
     * 大部分属性都赋予了默认值
     */
    public static class StyleBuilder {
        private String fontName = "DengXian";
        private int fontColor = Font.COLOR_NORMAL;
        private boolean fontBold = false;
        private int fontUnderline = Font.U_NONE;
        private boolean fontItalic = false;
        private int fontHeightInPoints = 12;
        /**
         * @see org.apache.poi.ss.usermodel.IndexedColors
         */
        private int foregroundColor;
        /**
         * @see org.apache.poi.ss.usermodel.IndexedColors
         */
        private int backgroundColor;
        private boolean needLeftBorder = true;
        private boolean needRightBorder = true;
        private boolean needTopBorder = true;
        private boolean needBottomBorder = true;
        private int borderColor = IndexedColors.GREY_50_PERCENT.index;
        private BorderStyle borderWeight = BorderStyle.THIN;
        private boolean autoLineFeed = false;
        private int alignment = 2;
        private int verticalAlignment = 2;
        private String datePattern;
        private String decimalPattern;

        StyleBuilder() {
        }

        public Style build() {
            return new Style(this.fontName, this.fontColor, this.fontBold, this.fontUnderline, this.fontItalic,
                    this.fontHeightInPoints, this.foregroundColor, this.backgroundColor, this.needLeftBorder,
                    this.needRightBorder, this.needTopBorder, this.needBottomBorder, this.borderColor, this.borderWeight,
                    this.autoLineFeed, this.alignment, this.verticalAlignment, this.datePattern, this.decimalPattern);
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this);
        }

        public StyleBuilder fontName(String fontName) {
            this.fontName = fontName;
            return this;
        }

        public StyleBuilder fontColor(int fontColor) {
            this.fontColor = fontColor;
            return this;
        }

        public StyleBuilder fontBold(boolean fontBold) {
            this.fontBold = fontBold;
            return this;
        }

        public StyleBuilder fontUnderline(int fontUnderline) {
            this.fontUnderline = fontUnderline;
            return this;
        }

        public StyleBuilder fontItalic(boolean fontItalic) {
            this.fontItalic = fontItalic;
            return this;
        }

        public StyleBuilder fontHeightInPoints(int fontHeightInPoints) {
            this.fontHeightInPoints = fontHeightInPoints;
            return this;
        }

        public StyleBuilder foregroundColor(int foregroundColor) {
            this.foregroundColor = foregroundColor;
            return this;
        }

        public StyleBuilder backgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public StyleBuilder needLeftBorder(boolean needLeftBorder) {
            this.needLeftBorder = needLeftBorder;
            return this;
        }

        public StyleBuilder needRightBorder(boolean needRightBorder) {
            this.needRightBorder = needRightBorder;
            return this;
        }

        public StyleBuilder needTopBorder(boolean needTopBorder) {
            this.needTopBorder = needTopBorder;
            return this;
        }

        public StyleBuilder needBottomBorder(boolean needBottomBorder) {
            this.needBottomBorder = needBottomBorder;
            return this;
        }

        public StyleBuilder borderColor(int borderColor) {
            this.borderColor = borderColor;
            return this;
        }

        public StyleBuilder borderWeight(BorderStyle borderWeight) {
            this.borderWeight = borderWeight;
            return this;
        }

        public StyleBuilder autoLineFeed(boolean autoLineFeed) {
            this.autoLineFeed = autoLineFeed;
            return this;
        }

        public StyleBuilder alignment(int alignment) {
            this.alignment = alignment;
            return this;
        }

        public StyleBuilder verticalAlignment(int verticalAlignment) {
            this.verticalAlignment = verticalAlignment;
            return this;
        }

        public StyleBuilder datePattern(String datePattern) {
            this.datePattern = datePattern;
            return this;
        }

        public StyleBuilder decimalPattern(String decimalPattern) {
            this.decimalPattern = decimalPattern;
            return this;
        }
    }

}
