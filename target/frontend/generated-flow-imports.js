const div = document.createElement('div');
div.innerHTML = '<custom-style><style include="lumo-color lumo-typography"></style></custom-style>';
document.head.insertBefore(div.firstElementChild, document.head.firstChild);

import '@vaadin/vaadin-lumo-styles/color.js';
import '@vaadin/vaadin-lumo-styles/typography.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/icons.js';
import '@vaadin/vaadin-tabs/theme/lumo/vaadin-tab.js';
import '@vaadin/vaadin-ordered-layout/theme/lumo/vaadin-horizontal-layout.js';
import '@vaadin/vaadin-ordered-layout/theme/lumo/vaadin-vertical-layout.js';
import '@vaadin/vaadin-button/theme/lumo/vaadin-button.js';
import '@vaadin/flow-frontend/flow-component-renderer.js';
import '@vaadin/vaadin-dialog/theme/lumo/vaadin-dialog.js';
import '@vaadin/vaadin-text-field/theme/lumo/vaadin-text-field.js';
import '@vaadin/vaadin-tabs/theme/lumo/vaadin-tabs.js';
import '@vaadin/vaadin-login/theme/lumo/vaadin-login-form.js';