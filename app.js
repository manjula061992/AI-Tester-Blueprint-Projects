// app.js

const chatMessages = document.getElementById('chat-messages');
const userInput = document.getElementById('user-input');
const sendBtn = document.getElementById('send-btn');
const statusIndicator = document.querySelector('.status-indicator');

// Configuration
const OLLAMA_API_URL = 'http://localhost:11434/api/generate';
const MODEL_NAME = 'llama3.2';

// Prompt Template
const SYSTEM_PROMPT = `You are an expert QA Automation Engineer.
Your task is to generate comprehensive test cases based on the provided user requirements or code.
Follow this format for each test case:
- **Test Case ID**: [ID]
- **Description**: [What is being tested]
- **Pre-conditions**: [Setup required]
- **Steps**: [Step-by-step actions]
- **Expected Result**: [What should happen]

Generate only the test cases in Markdown format.
User Input:
`;

// Auto-resize textarea
userInput.addEventListener('input', function() {
    this.style.height = 'auto';
    this.style.height = (this.scrollHeight) + 'px';
    if(this.value === '') this.style.height = '24px';
});

// Send on Enter (Shift+Enter for new line)
userInput.addEventListener('keydown', (e) => {
    if (e.key === 'Enter' && !e.shiftKey) {
        e.preventDefault();
        handleSend();
    }
});

sendBtn.addEventListener('click', handleSend);

async function handleSend() {
    const text = userInput.value.trim();
    if (!text) return;

    // 1. Add User Message
    addMessage(text, 'user');
    userInput.value = '';
    userInput.style.height = '24px';

    // 2. Add Loading Indicator
    const loadingId = addLoading();
    
    // 3. Construct Payload
    const prompt = SYSTEM_PROMPT + text;
    
    try {
        const response = await fetch(OLLAMA_API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                model: MODEL_NAME,
                prompt: prompt,
                stream: false
            })
        });

        if (!response.ok) {
            throw new Error(`Ollama API Error: ${response.statusText}`);
        }

        const data = await response.json();
        const aiText = data.response;

        // 4. Update UI
        removeMessage(loadingId);
        addMessage(aiText, 'ai');

        statusIndicator.style.backgroundColor = '#22c55e'; // Green
        statusIndicator.title = 'Connected';

    } catch (error) {
        removeMessage(loadingId);
        addMessage(`**Error**: Could not connect to Ollama. \n\nDetails: ${error.message}\n\nPlease ensure Ollama is running (` + '`ollama serve`' + `) and allows cross-origin requests.`, 'ai');
        console.error(error);
        
        statusIndicator.style.backgroundColor = '#ef4444'; // Red
        statusIndicator.title = 'Disconnected or Error';
    }
}

function addMessage(text, sender) {
    const div = document.createElement('div');
    div.className = `message ${sender}`;
    
    if (sender === 'ai') {
        // Parse Markdown
        div.innerHTML = marked.parse(text);
    } else {
        div.textContent = text;
    }

    chatMessages.appendChild(div);
    scrollToBottom();
    return div.id = 'msg-' + Date.now();
}

function addLoading() {
    const div = document.createElement('div');
    div.className = 'message ai';
    div.id = 'loading-' + Date.now();
    div.innerHTML = `<div class="typing-indicator"><span></span><span></span><span></span></div>`;
    chatMessages.appendChild(div);
    scrollToBottom();
    return div.id;
}

function removeMessage(id) {
    const el = document.getElementById(id);
    if (el) el.remove();
}

function scrollToBottom() {
    const lastMsg = chatMessages.lastElementChild;
    lastMsg.scrollIntoView({ behavior: 'smooth' });
}
