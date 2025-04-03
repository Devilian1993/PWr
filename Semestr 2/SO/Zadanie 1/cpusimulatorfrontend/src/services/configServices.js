export const sendConfig = async (config) => {
    const response = await fetch(`/api/simulations`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(config),
    });

    return await response.json();
}