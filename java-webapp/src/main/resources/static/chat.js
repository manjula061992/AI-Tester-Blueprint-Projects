let currentTestcaseId = null;

async function sendMessage() {
    const input = document.getElementById('userInput');
    const userText = input.value.trim();

    if (!userText) return;

    // Add user message to chat
    addMessage('user', userText);
    input.value = '';

    // Show loading state
    addMessage('bot', '🔄 Generating testcases...');

    try {
        // Call backend API to generate testcases
        const response = await fetch('/api/generate', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ input: userText })
        });

        if (!response.ok) {
            throw new Error('Failed to generate testcases');
        }

        const data = await response.json();
        currentTestcaseId = data.id;

        // Remove loading message
        const messages = document.getElementById('chatMessages');
        const loadingMessage = messages.lastChild;
        if (loadingMessage.classList.contains('bot-message')) {
            messages.removeChild(loadingMessage);
        }

        // Display success message
        addMessage('bot', `✅ Generated ${data.summary.total} testcases!`);

        // Display testcases in output panel
        displayTestcases(data.testcases);

        // Show export button
        document.getElementById('exportBtn').style.display = 'block';

    } catch (error) {
        console.error('Error:', error);
        addMessage('bot', '❌ Error generating testcases. Please try again. Error: ' + error.message);
    }
}

function addMessage(sender, text) {
    const messagesContainer = document.getElementById('chatMessages');
    const messageDiv = document.createElement('div');
    messageDiv.className = `message ${sender === 'user' ? 'user-message' : 'bot-message'}`;

    const contentDiv = document.createElement('div');
    contentDiv.className = 'message-content';
    contentDiv.innerHTML = `<p>${escapeHtml(text)}</p>`;

    messageDiv.appendChild(contentDiv);
    messagesContainer.appendChild(messageDiv);

    // Auto-scroll to bottom
    messagesContainer.scrollTop = messagesContainer.scrollHeight;
}

function displayTestcases(testcases) {
    const outputContainer = document.getElementById('outputContent');
    outputContainer.innerHTML = '';

    const summary = document.createElement('div');
    summary.className = 'testcase-summary';
    summary.innerHTML = `<h3>📋 ${testcases.length} Testcases Generated</h3>`;
    outputContainer.appendChild(summary);

    testcases.forEach((tc, index) => {
        const tcDiv = document.createElement('div');
        tcDiv.className = 'testcase-item';
        const priorityClass = tc.priority ? tc.priority.toLowerCase() : 'medium';
        const categoryClass = tc.category ? tc.category.toLowerCase() : 'general';
        
        let actionsHtml = '';
        if (tc.act && Array.isArray(tc.act)) {
            actionsHtml = `
                <details>
                    <summary>Act (Steps)</summary>
                    <ol>
                        ${tc.act.map(step => `<li>${escapeHtml(step)}</li>`).join('')}
                    </ol>
                </details>
            `;
        }

        let assertionsHtml = '';
        if (tc.assertions) {
            assertionsHtml = `
                <details>
                    <summary>Assert (Expected Results)</summary>
                    <pre>${escapeHtml(JSON.stringify(tc.assertions, null, 2))}</pre>
                </details>
            `;
        }

        let codeHtml = '';
        if (tc.codeSnippet) {
            codeHtml = `
                <details>
                    <summary>Code Snippet</summary>
                    <pre><code>${escapeHtml(tc.codeSnippet)}</code></pre>
                </details>
            `;
        }

        tcDiv.innerHTML = `
            <div class="testcase-header">
                <span class="tc-id">${escapeHtml(tc.id)}</span>
                <span class="tc-priority ${priorityClass}">${escapeHtml(tc.priority || 'N/A')}</span>
                <span class="tc-category ${categoryClass}">${escapeHtml(tc.category || 'N/A')}</span>
            </div>
            <div class="testcase-content">
                <h4>${escapeHtml(tc.title)}</h4>
                <p class="description">${escapeHtml(tc.description)}</p>
                
                <details>
                    <summary>Arrange (Setup)</summary>
                    <pre>${escapeHtml(JSON.stringify(tc.arrange, null, 2))}</pre>
                </details>
                
                ${actionsHtml}
                ${assertionsHtml}
                ${codeHtml}
            </div>
        `;
        outputContainer.appendChild(tcDiv);
    });
}

async function exportTestcases() {
    if (!currentTestcaseId) {
        alert('No testcases to export');
        return;
    }

    try {
        const response = await fetch(`/api/export/${currentTestcaseId}`);
        const data = await response.text();

        // Create and download file
        const element = document.createElement('a');
        element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(data));
        element.setAttribute('download', `testcases_${currentTestcaseId}.py`);
        element.style.display = 'none';
        document.body.appendChild(element);
        element.click();
        document.body.removeChild(element);

        addMessage('bot', '📥 Testcases exported as Python file!');
    } catch (error) {
        console.error('Error exporting:', error);
        alert('Failed to export testcases');
    }
}

function handleKeyPress(event) {
    if (event.key === 'Enter') {
        sendMessage();
    }
}

function escapeHtml(text) {
    const map = {
        '&': '&amp;',
        '<': '&lt;',
        '>': '&gt;',
        '"': '&quot;',
        "'": '&#039;'
    };
    return text.replace(/[&<>"']/g, m => map[m]);
}
