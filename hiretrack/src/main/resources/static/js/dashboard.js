const API_URL = "http://localhost:8080/api/jobs";

AOS.init();

async function loadDashboard() {
    const totalRes = await fetch(`${API_URL}/dashboard/total`);
    const total = await totalRes.json();

    const statsRes = await fetch(`${API_URL}/dashboard/stats`);
    const stats = await statsRes.json();

    document.getElementById("totalCount").innerText = total;
    document.getElementById("interviewCount").innerText = stats.INTERVIEW || 0;
    document.getElementById("offerCount").innerText = stats.OFFER || 0;
    document.getElementById("rejectedCount").innerText = stats.REJECTED || 0;

    const ctx = document.getElementById("statusChart");
    new Chart(ctx, {
        type: "pie",
        data: {
            labels: Object.keys(stats),
            datasets: [{
                data: Object.values(stats),
                backgroundColor: ["#6c757d", "#0dcaf0", "#0d6efd", "#198754", "#dc3545"]
            }]
        }
    });
}

loadDashboard();