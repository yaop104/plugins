/* ===================================================
 * dtooltip.js v0.5
 * ===================================================
 * Copyright 2012 Kim Lindhardt Madsen
 *
 * Released under the MIT and GPL licenses.
 * ========================================================== */
(function (jQuery) {

    jQuery.extend({
        dTooltip: {
            version: 0.5,
            defaults: {
                angle: 180,
                cssClass: 'dtooltip',
                spacing: 5,
                offset: {top: 0, left: 0},
                content: '<p>Insert html</p>',
                showEvent: 'mouseenter',
                hideEvent: 'mouseleave',
                showDelay: 0,
                hideDelay: 0
            }
        }
    });

    jQuery.fn.extend({
        dTooltip: function (options) {
            // --------------------------- INITIALIZE ----------------------- //
            var that = this;
            var thisOptions = jQuery.extend({}, jQuery.dTooltip.defaults, options);

            // Create elements
            var uniqueID = 'dTooltip_' + this.attr("id");
            var toolTipElement = jQuery(document.createElement('div'))
                .addClass(thisOptions.cssClass)
                .attr("id", uniqueID)
                .css("display", "none");

            // --------------------------- FUNCTIONS ----------------------- //
            var returnObject = {};

            function reposition() {
                var $element = $(that);
                var totalSpacing = thisOptions.spacing;
                if (thisOptions.arrowSize) {
                    totalSpacing += thisOptions.arrowSize;
                }
                var tooltipPosition = calculatePosition($element, toolTipElement, thisOptions.angle, totalSpacing, thisOptions.offset);
                position(toolTipElement, tooltipPosition[0], tooltipPosition[1]);
            }

            function setHtml(html) {
                toolTipElement.html(getValue(html));   
            }

            function show() {
                reposition();
                if (thisOptions.show) {
                    thisOptions.show.call(returnObject, toolTipElement);
                } else {
                    revealTooltip(toolTipElement);
                }
            }

            function hide() {
                 if (thisOptions.remove) {
                    thisOptions.hide.call(returnObject, toolTipElement);
                } else {
                    hideTooltip(toolTipElement);
                }
            }

            function remove() {
                removeTooltip(toolTipElement);
            }

            // --------------------------- EVENTS ----------------------- //

            var cancelHide = false;
            var cancelShow = false;
            if (thisOptions.showEvent) {
                this.bind(thisOptions.showEvent, function(e) {
                    cancelShow = false;
                    cancelHide = true;
                    window.setTimeout(function() {
                        if (!cancelShow) {
                            show();
                        }
                    }, thisOptions.showDelay);
                });
            }
            if (thisOptions.hideEvent) {
                this.bind(thisOptions.hideEvent, function(e) {
                    cancelShow = true;
                    cancelHide = false;
                    window.setTimeout(function() {
                        if (!cancelHide) {
                            hide();
                        }
                    }, thisOptions.hideDelay);
                });
            }

            // Bind hover events to the tooltip
            this.hover(
                function (e) {
                    // on hover
                    $(toolTipElement).addClass('hover');
                },
                function (e) {
                    // off hover
                    $(toolTipElement).removeClass('hover');
                }
            );
            
            // -------------------------- ELEMENTS ----------------------------------------- //
            toolTipElement.html(getValue(thisOptions.content))
            jQuery(document.body).append(toolTipElement);
            if (thisOptions.arrowSize) {
                createPseudoRules(toolTipElement, thisOptions.angle, thisOptions.arrowSize, thisOptions.cssClass);
                createPseudoRules(toolTipElement, thisOptions.angle, thisOptions.arrowSize, thisOptions.cssClass, true);
            }

            // -------------------------- RETURN OBJECT ------------------------ //
            returnObject.element = toolTipElement;
            returnObject.reposition = reposition;
            returnObject.reveal = show;
            returnObject.hide = hide;
            returnObject.remove = remove;
            returnObject.setHtml = setHtml; 
            return returnObject;
        }
    });

    function getValue(value) {
        if (typeof(value) == "function") {
            return getValue(value());
        } else {
            return value;
        }
    }

    function createPseudoRules(element, angle, arrowWidth, styleName, hoverStyle) {
        var style = getOrCreateTooltipChangeStyleSheet(element[0].ownerDocument);
        var styleSheet;
        if (style.styleSheet) {
            styleSheet = style.styleSheet;
        }
        if (style.sheet) {
            styleSheet = style.sheet;
        }

        var direction, opposite, next, arrowOffset;
        if (angle === 180) {
            direction = "Top";
            opposite = "Bottom";
            next = 'Right';
            arrowOffset = element.width() / 2;
        } else if (angle === 90) {
            direction = "Left";
            opposite = "Right";
            next = "Bottom";
            arrowOffset = element.height() / 2;
        } else if ((angle === 0) || (angle === 360)) {
            direction = "Bottom";
            opposite = "Top";
            next = 'Left';
            arrowOffset = element.width() / 2;
        } else if (angle === 270) {
            direction = "Right";
            opposite = "Left";
            next = "Top";
            arrowOffset = element.height() / 2;
        } else {
            throw 'angle is not supported';
        }

        // --------------- STYLING -----------------//

        // Do not mark any text
        element.css('-webkit-touch-callout', 'none')
            .css('-webkit-user-select', 'none')
            .css('-khtml-user-select', 'none')
            .css('-moz-user-select', 'none')
            .css('-ms-user-select', 'none')
            .css('user-select', 'none');

        var borderWidth, borderColor, borderType, backgroundColor;
        if (hoverStyle) {
            var elementDocument = element[0].ownerDocument;
            var tempElement = jQuery(elementDocument.createElement('div'));
            tempElement.addClass(styleName).addClass('hover');
            jQuery(elementDocument.body).append(tempElement);
            borderWidth = parseInt(tempElement.css('border' + direction + 'Width'), 10);
            borderColor = tempElement.css('border' + direction + 'Color');
            borderType = tempElement.css('border' + direction + 'Style');
            backgroundColor = tempElement.css('backgroundColor');
            tempElement.remove();
        } else {
            borderWidth = parseInt(element.css('border' + direction + 'Width'), 10);
            borderColor = element.css('border' + direction + 'Color');
            borderType = element.css('border' + direction + 'Style');
            backgroundColor = element.css('backgroundColor');
        }
        // Some fallback values
        if (!borderWidth) {
            borderWidth = 0;
            borderColor = backgroundColor;
        }
        if (!borderType || borderType === 'none') {
            borderType = 'solid';
        }

        var elementId = "#" + element.attr("id");
        var afterName = elementId + "." + styleName;
        if (hoverStyle) {
            afterName += '.hover';
        }
        afterName += ":after";
        this.afterRule = getOrCreateRule(styleSheet, afterName);
        if (this.afterRule) {
            var afterStyle = this.afterRule.style;
            stylePseudoElement(afterStyle,
                direction,
                opposite,
                next,
                arrowWidth - borderWidth,
                borderType,
                arrowWidth - borderWidth,
                backgroundColor,
                arrowOffset);
        }
        var beforeName = elementId + "." + styleName;
        if (hoverStyle) {
            beforeName += '.hover';
        }
        beforeName += ":before";
        this.beforeRule = getOrCreateRule(styleSheet, beforeName);
        if (this.beforeRule) {
            var beforeStyle = this.beforeRule.style;
            stylePseudoElement(beforeStyle,
                direction,
                opposite,
                next,
                arrowWidth,
                borderType,
                arrowWidth,
                borderColor,
                arrowOffset - borderWidth);
        }

        return [this.beforeRule, this.afterRule];
    }

    function stylePseudoElement(style, direction, opposite, next, borderWidth, borderType, borderOppositeWidth, borderOppositeColor, nextOffset) {
        style['borderWidth'] = borderWidth + "px";
        style['borderColor'] = "transparent";
        style['borderStyle'] = borderType;
        style['border' + direction + 'Width'] = "0px";
        style['border' + opposite + 'Width'] = borderOppositeWidth + "px";
        style['border' + opposite + 'Color'] = borderOppositeColor;
        style.position = "absolute";
        style['height'] = "0";
        style['width'] = "0";
        style[opposite.toLowerCase()] = "100%";
        style[next.toLowerCase()] = nextOffset + "px";
        style.display = "inline-block";
        try {
            style.content = "\"\"";
        } catch (ignored) {
        }
    }

    function calculatePosition(element, tooltip, angle, spacing, offset) {
        // error checking on degrees
        if (angle > 360 || angle < 0) {
            throw 'You have misconfigured the tooltip - please choose from an angle between 0-360 degrees for the object: #' + element.attr("id");
        }

        var elementOffset = element.offset();
        var elementWidth, elementHeight, tooltipWidth, tooltipHeight;
        elementWidth = element.width() + getHorizontalPadding(element);
        elementHeight = element.height() + getVerticalPadding(element);
        tooltipWidth = tooltip.width() + getHorizontalPadding(tooltip);
        tooltipHeight = tooltip.height() + getVerticalPadding(tooltip);
        var position = [];      // x=[0], y=[1]
        if  ((angle === 0) || (angle === 360)) {
            // top
            position[0] = elementOffset.left + Math.round(elementWidth / 2) - Math.round(tooltipWidth / 2);
            position[1] = elementOffset.top - (tooltipHeight + spacing);
        } else if (angle === 90) {
            // right
            position[0] = elementOffset.left + elementWidth + spacing;
            position[1] = elementOffset.top + Math.round(elementHeight / 2 - tooltipHeight / 2);
        } else if (angle === 180) {
            // bottom
            position[0] = elementOffset.left + Math.round(elementWidth / 2) - Math.round(tooltipWidth / 2);
            position[1] = elementOffset.top + elementHeight + spacing;
        } else if (angle === 270) {
            // left
            position[0] = elementOffset.left - (tooltipWidth + spacing);
            position[1] = elementOffset.top + Math.round(elementHeight / 2 - tooltipHeight / 2);
        } else {
                throw 'angle is not at this time';
        }

        position[0] = position[0] + offset.left;
        position[1] = position[1] + offset.top;

        return position;
    }

    function revealTooltip(tooltip) {
        tooltip.fadeIn(200);
    }

    function hideTooltip(tooltip) {
        tooltip.fadeOut(200);
    }

    function removeTooltip(tooltip) {
        tooltip.remove();
    }

    function getOrCreateTooltipChangeStyleSheet(usedDocument) {
        if (!jQuery.dTooltip.dynamicStyle) {
            var head = usedDocument.getElementsByTagName('head')[0],
                style = usedDocument.createElement('style');
            style.type = 'text/css';
            head.appendChild(style);
            jQuery.dTooltip.dynamicStyle = style;
        }
        return jQuery.dTooltip.dynamicStyle;
    }

    // -------------------------- STYLING UTILITIES ---------------------------------- //

    function getVerticalPadding(element) {
        return element.outerWidth() - element.width();
    }

    function getHorizontalPadding(element) {
        return element.outerWidth() - element.width();
    }

    function position(element, x, y) {
        element.css("position", "absolute");
        element.css("top", y);
        element.css("left", x);
    }

    function getRule(document, rule) {
        var styleSheets = document.styleSheets;
        var hoverStyle = null, index = 0;
        while (!hoverStyle && index < styleSheets.length) {
            hoverStyle = getRuleFromStyleSheet(styleSheets[index], rule);
            index++;
        }
        return hoverStyle;
    }

    function getOrCreateRule(styleSheet, ruleName) {
        var rule = getRuleFromStyleSheet(styleSheet, ruleName);
        if (rule) {
            return rule;
        }
        else {
            if (styleSheet.addRule) {
                styleSheet.addRule(ruleName);
            } else if (styleSheet.insertRule) {
                styleSheet.insertRule(ruleName + "{}", 0);
            }
            return getRuleFromStyleSheet(styleSheet, ruleName);
        }
    }

    function getRuleFromStyleSheet(styleSheet, ruleName) {
        var styleRules = styleSheet.cssRules ? styleSheet.cssRules : styleSheet.rules;
        if (styleRules) {
            for (var i = 0; i < styleRules.length; i++) {
                var styleRule = styleRules[i];
                if (styleRule.selectorText &&
                    styleRule.selectorText.toLowerCase().replace(/:/g, "") == ruleName.toLowerCase().replace(/:/g, "")) {
                    return styleRule;
                }
            }
        }
        return null;

    }

})(jQuery);