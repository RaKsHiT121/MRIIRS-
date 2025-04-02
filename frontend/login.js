document.getElementById("loginForm").addEventListener("submit", async function(event) {
    event.preventDefault(); // Prevent form default submission

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const response = await fetch("http://localhost:8080/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    });

    const data = await response.json();
    
    if (data.success) {
        alert("Login successful! Redirecting...");
        if (data.role === "STUDENT") {
            window.location.href = "student_dashboard.html"; // Redirect to Student Page
        } else if (data.role === "FACULTY") {
            window.location.href = "faculty_dashboard.html"; // Redirect to Faculty Page
        }
    } else {
        alert("Invalid credentials! Please try again.");
    }
});
