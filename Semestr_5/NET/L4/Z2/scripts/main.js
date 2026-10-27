import { isCanvasEmpty, clearCanvas } from './utils.js';
import { initSignature } from './signature.js';
import { initDateCanvas } from './date.js';

const signatureCanvas = document.getElementById('signatureCanvas');
const dateCanvas = document.getElementById('dateCanvas');
const submit = document.getElementById('submit');
const reset = document.getElementById('reset');
const printBtn = document.getElementById('printBtn');
const exportBtn = document.getElementById('exportBtn');

const signatureCtx = initSignature(signatureCanvas);
const dateCtx = initDateCanvas(dateCanvas);

submit.addEventListener('click', (e) => {
    if (isCanvasEmpty(signatureCanvas) || isCanvasEmpty(dateCanvas)) {
        alert("Proszę uzupełnić datę i złożyć podpis przed zatwierdzeniem umowy!");
        return;
    }

    const dataURL = signatureCanvas.toDataURL('image/png');
    const signatureDiv = document.getElementById('signatureDiv');
    signatureDiv.innerHTML = `<img src="${dataURL}" alt="Signature">`;

    dateCanvas.style.border = 'none';

    submit.remove();
    reset.remove();

    printBtn.style.display = 'inline';
    exportBtn.style.display = 'inline';
});

reset.addEventListener('click', () => {
    clearCanvas(signatureCanvas, signatureCtx);
    clearCanvas(dateCanvas, dateCtx);
});

printBtn.addEventListener('click', () => {
    window.print();
});

exportBtn.addEventListener('click', () => {
    const dataURL = signatureCanvas.toDataURL('image/png');
    const link = document.createElement('a');
    link.href = dataURL;
    link.download = 'signature.png';
    link.click();
});