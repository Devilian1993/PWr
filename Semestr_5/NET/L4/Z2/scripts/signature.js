export const initSignature = (canvas) => {
    const ctx = canvas.getContext('2d');
    let isDrawing = false;

    const startDrawing = (e) => {
        isDrawing = true;
        draw(e);
    };

    const stopDrawing = () => {
        isDrawing = false;
        ctx.beginPath();
    };

    const draw = (e) => {
        if (!isDrawing) return;

        const rect = canvas.getBoundingClientRect();
        const clientX = e.type.includes('mouse') ? e.clientX : e.touches[0].clientX;
        const clientY = e.type.includes('mouse') ? e.clientY : e.touches[0].clientY;

        const x = clientX - rect.left;
        const y = clientY - rect.top;

        ctx.lineWidth = 2;
        ctx.lineCap = 'round';
        ctx.strokeStyle = 'black';

        ctx.lineTo(x, y);
        ctx.stroke();

        ctx.beginPath();
        ctx.moveTo(x, y);

        if (e.type === 'touchmove') {
            e.preventDefault();
        }
    };

    canvas.addEventListener('mousedown', startDrawing);
    canvas.addEventListener('mousemove', draw);
    canvas.addEventListener('mouseup', stopDrawing);
    canvas.addEventListener('mouseout', stopDrawing);

    canvas.addEventListener('touchstart', startDrawing);
    canvas.addEventListener('touchmove', draw, { passive: false });
    canvas.addEventListener('touchend', stopDrawing);

    return ctx;
};