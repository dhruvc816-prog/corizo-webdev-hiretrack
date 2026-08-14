const API_URL = "http://localhost:8080/api/jobs";
const params = new URLSearchParams(window.location.search);
const id = params.get("id");

if (id) {
    document.getElementById("formTitle").innerText = "Edit Application";
    loadApplication(id);
}

async function loadApplication(id) {
    const res = await fetch(`${API_URL}/applications/${id}`);
    const app = await res.json();

    document.getElementById("id").value = app.id;
    document.getElementById("companyName").value = app.companyName;
    document.getElementById("domain").value = app.domain;
    document.getElementById("role").value = app.role;
    document.getElementById("appliedDate").value = app.appliedDate;
    document.getElementById("status").value = app.status;
    document.getElementById("notes").value = app.notes;
}

document.getElementById("appForm").addEventListener("submit", async function (e) {
    e.preventDefault();

    const data = {
        companyName: document.getElementById("companyName").value,
        domain: document.getElementById("domain").value,
        role: document.getElementById("role").value,
        appliedDate: document.getElementById("appliedDate").value,
        status: document.getElementById("status").value,
        notes: document.getElementById("notes").value
    };

    let response;
    try {
        if (id) {
            response = await fetch(`${API_URL}/updateApplications/${id}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(data)
            });
        } else {
            response = await fetch(`${API_URL}/createApplications`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(data)
            });
        }
    } catch (err) {
        alert("Network error: could not reach server. Is the backend running?");
        return;
    }

    if (response.ok) {
        window.location.href = "index.html";
    } else {
        const error = await response.json();
        alert("Error: " + JSON.stringify(error));
    }
});