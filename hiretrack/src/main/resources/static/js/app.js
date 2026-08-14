const API_URL = "http://localhost:8080/api/jobs";

AOS.init();

function getStatusBadgeClass(status) {
    const map = {
        PENDING: "badge-pending",
        ASSESSMENT: "badge-assessment",
        INTERVIEW: "badge-interview",
        OFFER: "badge-offer",
        REJECTED: "badge-rejected"
    };
    return map[status] || "bg-secondary";
}

async function loadApplications() {
    const res = await fetch(`${API_URL}/applications`);
    const data = await res.json();

    const tbody = document.getElementById("appTable");
    tbody.innerHTML = "";

    data.forEach((app, index) => {
        const row = document.createElement("tr");
        row.setAttribute("data-aos", "fade-up");
        row.setAttribute("data-aos-delay", (index * 50).toString());
        row.innerHTML = `
            <td><img src="https://logo.clearbit.com/${app.domain}" width="35" height="35" onerror="this.src='https://via.placeholder.com/35'"></td>
            <td>${app.companyName}</td>
            <td>${app.role}</td>
            <td>${app.appliedDate}</td>
            <td><span class="badge ${getStatusBadgeClass(app.status)}">${app.status}</span></td>
            <td>
                <a href="form.html?id=${app.id}" class="btn btn-sm btn-warning">Edit</a>
                <button class="btn btn-sm btn-danger" onclick="deleteApplication(${app.id})">Delete</button>
            </td>
        `;
        tbody.appendChild(row);
    });

    AOS.refresh();
}

async function deleteApplication(id) {
    if (!confirm("Delete this application?")) return;
    await fetch(`${API_URL}/applications/${id}`, { method: "DELETE" });
    loadApplications();
}

loadApplications();