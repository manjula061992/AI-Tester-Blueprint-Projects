/**
 * Dashboard JavaScript
 * Handles real-time status updates and interactive dashboard features
 */

document.addEventListener('DOMContentLoaded', function() {
    console.log('Dashboard loaded');
    
    // Fetch system status on load
    fetchSystemStatus();
    
    // Refresh status every 10 seconds
    setInterval(fetchSystemStatus, 10000);
});

async function fetchSystemStatus() {
    try {
        const response = await fetch('/api/status');
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const data = await response.json();
        updateStatusDisplay(data);
    } catch (error) {
        console.error('Error fetching status:', error);
        showErrorStatus(error.message);
    }
}

function updateStatusDisplay(data) {
    // Update overall status
    const statusText = document.getElementById('statusText');
    const statusIndicator = document.querySelector('.pulse');
    
    statusText.textContent = 'System Operational';
    statusIndicator.style.background = '#28a745';
    
    // Update Copilot status card
    const copilotData = data.copilot;
    updateCopilotCard(copilotData);
}

function updateCopilotCard(copilotData) {
    const badge = document.getElementById('copilotBadge');
    const message = document.getElementById('copilotMessage');
    const card = document.getElementById('copilotCard');
    
    // Update badge and styling based on status
    if (copilotData.connected) {
        badge.textContent = '✅ Connected';
        badge.className = 'badge success';
        card.style.borderLeft = '4px solid #28a745';
        message.innerHTML = `<strong style="color: #28a745;">✅ Connected</strong><br>GitHub Copilot is available<br>Status: ${copilotData.status}`;
    } else {
        badge.textContent = '⚠️ Unavailable';
        badge.className = 'badge';
        badge.style.background = '#ffc107';
        badge.style.color = '#333';
        card.style.borderLeft = '4px solid #ffc107';
        message.innerHTML = `<strong style="color: #ff9800;">⚠️ Mock Mode</strong><br>Using fallback testcases for development/testing<br>Message: ${copilotData.message}`;
    }
}

function showErrorStatus(errorMessage) {
    const statusText = document.getElementById('statusText');
    const statusIndicator = document.querySelector('.pulse');
    
    statusText.textContent = '⚠️ Error checking status';
    statusIndicator.style.background = '#dc3545';
    
    const badge = document.getElementById('copilotBadge');
    const message = document.getElementById('copilotMessage');
    
    badge.textContent = '❌ Unknown';
    message.innerHTML = `<strong style="color: #dc3545;">Unable to fetch status:</strong><br>${errorMessage}`;
}

// Smooth scrolling for navigation
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function(e) {
        e.preventDefault();
        const target = document.querySelector(this.getAttribute('href'));
        if (target) {
            target.scrollIntoView({ behavior: 'smooth' });
        }
    });
});

// Format JSON in console logs for debugging
console.log('📊 Testcase Generator Dashboard v1.0.0 (Java Spring Boot)');
console.log('🔗 API Endpoints:');
console.log('  - GET  /api/status - System status');
console.log('  - POST /api/generate - Generate testcases');
console.log('  - GET  /api/testcases/:id - Get testcases');
console.log('  - GET  /api/export/:id - Export as Python');
console.log('  - GET  /api/health - Health check');
console.log('\n💡 Tip: Open Chat Generator to start creating testcases!');
