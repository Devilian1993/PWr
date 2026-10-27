export const isCanvasEmpty = (canvas) => {
    const blank = document.createElement('canvas');
    blank.width = canvas.width;
    blank.height = canvas.height;
    return canvas.toDataURL() === blank.toDataURL();
};

export const clearCanvas = (canvas, ctx) => {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
};