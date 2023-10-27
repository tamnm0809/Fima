const labels = ["T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "T11", "T12"];

const data = {
    labels: labels,
    datasets: [
        {
            label: "Doanh thu",
            background: "blue",
            data: [0, 415000, 316000, 206000, 800000, 901000, 200000, 415000, 316000, 206000, 800000, 901000],
            tension: 0.4,
        },
    ],
};
const config = {
    type: "line",
    data: data,
};
const canvas = document.getElementById("canvas");
const doughnut = document.querySelector(".doughnut");

const chart = new Chart(canvas, config);

const chartData = {
    labels: ["Red", "Blue", "Yellow", "Green", "Gray"],
    data: [30, 50, 100, 20, 40],
};

new Chart(doughnut, {
    type: "doughnut",
    data: {
        lables: chartData.lables,
        datasets: [
            {
                label: "My First Dataset",
                data: chartData.data,
                backgroundColor: ["rgb(255, 99, 132)", "rgb(54, 162, 235)", "rgb(255, 205, 86)", "#305349", "#d6d6d6"],
                hoverOffset: 4,
            },
        ],
    },
});