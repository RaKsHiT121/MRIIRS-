
document.addEventListener("DOMContentLoaded", async () => {
        const studentId = localStorage.getItem("studentId");
    
        if (!studentId) {
            console.error("Student ID not found in localStorage");
            document.getElementById("query-list").innerHTML = "<p>Error: Student ID missing!</p>";
            return;
        }
    
        try {
            const response = await fetch(`http://localhost:8080/api/getStudentQueries?studentId=${studentId}`);
            
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
    
            const data = await response.json();
            console.log("API Response:", data); // Log the response for debugging
    
            if (!Array.isArray(data)) {
                throw new Error("Received unexpected response format");
            }
    
            const queryList = document.getElementById("query-list");
            queryList.innerHTML = ""; // Clear previous content
    
            if (data.length === 0) {
                queryList.innerHTML = "<p>No past queries.</p>";
            } else {
                data.forEach(query => {
                    const queryItem = document.createElement("p");
                    queryItem.textContent = `Query: ${query.description} (Status: ${query.status})`;
                    queryList.appendChild(queryItem);
                });
            }
        } catch (error) {
            console.error("Error fetching queries:", error);
            document.getElementById("query-list").innerHTML = "<p>Failed to load queries.</p>";
        }
    });
    document.addEventListener("DOMContentLoaded", async () => {
        const studentId = localStorage.getItem("studentId");
    
        if (!studentId) {
            console.error("Student ID not found in localStorage");
            document.getElementById("query-list").innerHTML = "<p>Error: Student ID missing!</p>";
            return;
        }
    
        try {
            const response = await fetch(`http://localhost:8080/api/getStudentQueries?studentId=${studentId}`);
            if (!response.ok) throw new Error(`HTTP error! Status: ${response.status}`);
    
            const queries = await response.json();
            if (!Array.isArray(queries)) throw new Error("Unexpected API response");
    
            const queryList = document.getElementById("query-list");
            queryList.innerHTML = ""; // Clear previous content
    
            if (queries.length === 0) {
                queryList.innerHTML = "<p>No past queries.</p>";
            } else {
                queries.forEach(query => {
                    const queryItem = document.createElement("div");
                    queryItem.classList.add("query-item");
                    queryItem.textContent = query.description;
                    queryItem.addEventListener("click", () => loadQueryDetails(query));
                    queryList.appendChild(queryItem);
                });
            }
        } catch (error) {
            console.error("Error fetching queries:", error);
            document.getElementById("query-list").innerHTML = "<p>Failed to load queries.</p>";
        }
    });
    
    function loadQueryDetails(query) {
        document.getElementById("query-title").textContent = `Query: ${query.description}`;
        document.getElementById("chat-box").innerHTML = `<p>Status: ${query.status}</p><p>${query.description}</p>`;
    }