export const initDateCanvas = (canvas) => {
    const ctx = canvas.getContext('2d');

    canvas.addEventListener('click', () => {
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        const date = new Date();
        const formattedDate = date.toLocaleDateString();
        ctx.font = '24px Arial';
        ctx.fillText(formattedDate, 10, 30);
    });

    return ctx;
};