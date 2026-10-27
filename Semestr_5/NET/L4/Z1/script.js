const form = document.getElementById('form');
const inputNumber = document.getElementById('input');
const resultContainer = document.getElementById('result');

form.addEventListener('submit', (e) => {
    e.preventDefault();
    resultContainer.innerHTML = "";

    const existingError = document.getElementById('error-msg');
    if (existingError) {
        existingError.remove();
    }

    const number = Number(inputNumber.value);

    if (number >= 5 && number <= 20) {
        drawTable(number);
    } else {
        errorAndDefault();
    }
});

const errorAndDefault = () => {
    const error = document.createElement("p");
    error.id = "error-msg";
    error.style.color = "red";
    error.textContent = "Tabela obsługuje liczby od 5 do 20. Przyjęto n = 5.";

    form.appendChild(error);

    inputNumber.value = 5;

    drawTable(5);
}

const generateRandomArray = (length, min, max) =>
    Array.from({ length }, () => Math.floor(Math.random() * (max - min + 1)) + min);

const generateTableNumbers = (firstRow, firstCol) =>
    firstCol.map(colVal =>
        firstRow.map(rowVal => colVal * rowVal)
    );

const drawTable = (n) => {

    const table = document.createElement("table");
    const firstRow = generateRandomArray(n, 1, 99);
    const firstCol = generateRandomArray(n, 1, 99);

    const tableNumbers = generateTableNumbers(firstRow, firstCol);

    const thead = document.createElement("thead");
    const headerRow = document.createElement("tr");

    const emptyTh = document.createElement("th");
    emptyTh.textContent = "";
    headerRow.appendChild(emptyTh);

    firstRow.forEach(num => {
        const th = document.createElement("th");
        th.textContent = num;
        headerRow.appendChild(th);
    });
    thead.appendChild(headerRow);
    table.appendChild(thead);

    const tbody = document.createElement("tbody");

    tableNumbers.forEach((rowValues, rowIndex) => {
        const tr = document.createElement("tr");

        const thRow = document.createElement("th");
        thRow.textContent = firstCol[rowIndex];
        tr.appendChild(thRow);

        rowValues.forEach(val => {
            const td = document.createElement("td");
            td.textContent = val;
            tr.appendChild(td);
            switch (val % 2 === 0) {
                case true: td.classList.add('even'); break;
                case false: td.classList.add('odd'); break;
            }
        });

        tbody.appendChild(tr);
    });

    table.appendChild(tbody);

    resultContainer.appendChild(table);
};