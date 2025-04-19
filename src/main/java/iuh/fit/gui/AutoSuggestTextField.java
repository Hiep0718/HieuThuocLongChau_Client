package iuh.fit.gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class AutoSuggestTextField extends JTextField {
    private JPopupMenu suggestionsPopup;
    private List<String> items;
    private int maxSuggestions = 10;
    private int minimumChars = 1;
    private boolean caseSensitive = false;
    private boolean matchStart = false;

    // Callback interface for suggestion selection
    public interface SuggestionSelectedListener {
        void onSuggestionSelected(String suggestion);
    }

    private List<SuggestionSelectedListener> selectionListeners = new ArrayList<>();

    /**
     * Creates an AutoSuggestTextField with the given list of suggestions
     * @param items List of suggestion items
     */
    public AutoSuggestTextField(List<String> items) {
        super();
        this.items = items != null ? items : new ArrayList<>();
        this.suggestionsPopup = new JPopupMenu();
        setupAutoComplete();
    }

    /**
     * Creates an empty AutoSuggestTextField with no suggestions
     */
    public AutoSuggestTextField() {
        this(new ArrayList<>());
    }

    /**
     * Add a listener that will be called when a suggestion is selected
     * @param listener The listener to be notified
     */
    public void addSuggestionSelectedListener(SuggestionSelectedListener listener) {
        if (listener != null) {
            selectionListeners.add(listener);
        }
    }

    /**
     * Remove a previously added suggestion selection listener
     * @param listener The listener to remove
     */
    public void removeSuggestionSelectedListener(SuggestionSelectedListener listener) {
        selectionListeners.remove(listener);
    }

    /**
     * Set the list of suggestions
     * @param items List of suggestion items
     */
    public void setItems(List<String> items) {
        this.items = items != null ? items : new ArrayList<>();
    }

    /**
     * Get the current list of suggestions
     * @return List of suggestion items
     */
    public List<String> getItems() {
        return items;
    }

    /**
     * Set the minimum number of characters required before showing suggestions
     * @param minimumChars Number of characters (default is 1)
     */
    public void setMinimumChars(int minimumChars) {
        this.minimumChars = minimumChars;
    }

    /**
     * Set maximum number of suggestions to display
     * @param maxSuggestions Maximum number of suggestions
     */
    public void setMaxSuggestions(int maxSuggestions) {
        this.maxSuggestions = maxSuggestions;
    }

    /**
     * Set whether matching should be case-sensitive
     * @param caseSensitive True for case-sensitive, false for case-insensitive
     */
    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    /**
     * Set whether to match from the start of the text or anywhere within
     * @param matchStart True to match from the start only, false to match anywhere
     */
    public void setMatchStart(boolean matchStart) {
        this.matchStart = matchStart;
    }

    private void setupAutoComplete() {
        // Initialize popup appearance
        suggestionsPopup.setFocusable(false);
        suggestionsPopup.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Add document listener to detect typing
        this.getDocument().addDocumentListener(new DocumentListener() {
            private void updateSuggestions() {
                SwingUtilities.invokeLater(() -> {
                    String text = getText();
                    String compareText = caseSensitive ? text : text.toLowerCase();

                    // Hide popup if text is too short
                    if (text.length() < minimumChars) {
                        suggestionsPopup.setVisible(false);
                        return;
                    }

                    // Filter items based on settings
                    List<String> filtered = items.stream()
                            .filter(item -> {
                                String compareItem = caseSensitive ? item : item.toLowerCase();
                                if (matchStart) {
                                    return compareItem.startsWith(compareText);
                                } else {
                                    return compareItem.contains(compareText);
                                }
                            })
                            .limit(maxSuggestions)
                            .collect(Collectors.toList());

                    // If no matches, hide popup
                    if (filtered.isEmpty()) {
                        suggestionsPopup.setVisible(false);
                        return;
                    }

                    // Build popup menu
                    buildSuggestionsMenu(filtered);
                });
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSuggestions();
            }
        });

        // Handle keyboard navigation in popup
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    suggestionsPopup.setVisible(false);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && suggestionsPopup.isVisible()) {
                    navigatePopup(true);
                    e.consume();
                } else if (e.getKeyCode() == KeyEvent.VK_UP && suggestionsPopup.isVisible()) {
                    navigatePopup(false);
                    e.consume();
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER && suggestionsPopup.isVisible()) {
                    selectCurrentItem();
                    e.consume();
                }
            }
        });

        // Hide popup when focus is lost
        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                // Small delay to allow for clicking on the popup
                Timer timer = new Timer(200, evt -> {
                    if (!suggestionsPopup.isVisible()) return;

                    Component oppositeComponent = e.getOppositeComponent();
                    if (oppositeComponent != null) {
                        // Check if focus went to one of our popup items
                        boolean isPopupComponent = false;
                        for (Component c : suggestionsPopup.getComponents()) {
                            if (c == oppositeComponent || SwingUtilities.isDescendingFrom(oppositeComponent, c)) {
                                isPopupComponent = true;
                                break;
                            }
                        }

                        if (!isPopupComponent) {
                            suggestionsPopup.setVisible(false);
                        }
                    } else {
                        suggestionsPopup.setVisible(false);
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });

        // Make popup disappear if window is resized or moved
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (suggestionsPopup.isVisible()) {
                    suggestionsPopup.setVisible(false);
                }
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                if (suggestionsPopup.isVisible()) {
                    suggestionsPopup.setVisible(false);
                }
            }
        });
    }

    private void buildSuggestionsMenu(List<String> suggestions) {
        suggestionsPopup.removeAll();

        for (String suggestion : suggestions) {
            JMenuItem item = new JMenuItem(suggestion);
            item.setBackground(Color.WHITE);
            item.setFont(getFont());

            // Highlight the matching part of the text
            if (!caseSensitive && !getText().isEmpty()) {
                String highlightText = getText().toLowerCase();
                String itemText = suggestion;
                int index = itemText.toLowerCase().indexOf(highlightText);

                if (index >= 0) {
                    String before = itemText.substring(0, index);
                    String match = itemText.substring(index, index + highlightText.length());
                    String after = itemText.substring(index + highlightText.length());

                    item.setText("<html>" + before + "<b>" + match + "</b>" + after + "</html>");
                }
            }

            item.addActionListener(e -> {
                // Set the text in this field
                setText(suggestion);
                suggestionsPopup.setVisible(false);
                requestFocus(); // Keep focus on text field

                // Notify all the listeners
                notifySuggestionSelected(suggestion);
            });

            suggestionsPopup.add(item);
        }

        // Calculate appropriate position and size
        Rectangle bounds = getBounds();
        int popupWidth = Math.max(bounds.width, 200);
        int popupHeight = Math.min(suggestions.size() * 24, 300);

        suggestionsPopup.setPopupSize(popupWidth, popupHeight);
        suggestionsPopup.show(this, 0, bounds.height);

        // Make sure popup is visible and not clipped by screen edges
        SwingUtilities.convertPointToScreen(bounds.getLocation(), this);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        Point popupLocation = suggestionsPopup.getLocationOnScreen();
        if (popupLocation.y + popupHeight > screenSize.height) {
            // Show above the text field instead
            suggestionsPopup.setVisible(false);
            suggestionsPopup.show(this, 0, -popupHeight);
        }
    }

    private void navigatePopup(boolean down) {
        int itemCount = suggestionsPopup.getComponentCount();
        if (itemCount == 0) return;

        // Find currently selected item
        int currentIndex = -1;
        for (int i = 0; i < itemCount; i++) {
            Component component = suggestionsPopup.getComponent(i);
            if (component instanceof JMenuItem && component.getBackground().equals(UIManager.getColor("MenuItem.selectionBackground"))) {
                currentIndex = i;
                break;
            }
        }

        // Calculate new index
        int newIndex;
        if (currentIndex == -1) {
            newIndex = down ? 0 : itemCount - 1;
        } else {
            newIndex = down ? (currentIndex + 1) % itemCount : (currentIndex - 1 + itemCount) % itemCount;
        }

        // Select new item
        for (int i = 0; i < itemCount; i++) {
            Component component = suggestionsPopup.getComponent(i);
            if (component instanceof JMenuItem) {
                JMenuItem item = (JMenuItem) component;
                if (i == newIndex) {
                    item.setBackground(UIManager.getColor("MenuItem.selectionBackground"));
                    item.setForeground(UIManager.getColor("MenuItem.selectionForeground"));
                } else {
                    item.setBackground(Color.WHITE);
                    item.setForeground(Color.BLACK);
                }
            }
        }
    }

    private void selectCurrentItem() {
        for (Component component : suggestionsPopup.getComponents()) {
            if (component instanceof JMenuItem &&
                    component.getBackground().equals(UIManager.getColor("MenuItem.selectionBackground"))) {
                ((JMenuItem) component).doClick();
                return;
            }
        }

        // If no item is selected, click the first one if available
        if (suggestionsPopup.getComponentCount() > 0) {
            Component component = suggestionsPopup.getComponent(0);
            if (component instanceof JMenuItem) {
                ((JMenuItem) component).doClick();
            }
        }
    }

    // Helper method to notify all listeners when a suggestion is selected
    private void notifySuggestionSelected(String suggestion) {
        for (SuggestionSelectedListener listener : selectionListeners) {
            listener.onSuggestionSelected(suggestion);
        }
    }

    // Convenience method to add a simple interaction with another text field
    public void linkToTextField(JTextField otherField, String fieldName) {
        addSuggestionSelectedListener(suggestion -> {
            // Lookup the customer with this phone number
            try {
                // Here's where you would normally lookup a customer by phone number
                // This is just a placeholder showing the concept
                otherField.setText("Customer with " + fieldName + ": " + suggestion);
            } catch (Exception ex) {
                otherField.setText("");
                System.err.println("Error looking up customer: " + ex.getMessage());
            }
        });
    }
}