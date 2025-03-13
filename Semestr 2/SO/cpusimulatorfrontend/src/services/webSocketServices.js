import SockJS from "sockjs-client"
import { Stomp } from "@stomp/stompjs"

let stompClient = null
let isConnected = false
let messageQueue = []

export const connectWebSocket = (onConnect, onSimulationUpdate) => {
    if (stompClient !== null) {
        return
    }

    const socket = new SockJS("http://localhost:8080/simulation-websocket")
    stompClient = Stomp.over(socket)

    stompClient.connect({}, frame => {
        console.log("Connected to websocket: ", frame)
        isConnected = true

        stompClient.subscribe('/topic/simulation', message => {
            const data = JSON.parse(message.body);
            onSimulationUpdate(data.algorithm, data.state);
        });

        while (messageQueue.length > 0) {
            const { destination, body } = messageQueue.shift();
            sendMessage(destination, body);
        }
    })

    return () => {
        if (stompClient) {
            stompClient.disconnect();
            stompClient = null;
            isConnected = false;
        }
    }
}

export const sendMessage = (destination, body) => {
    if (!isConnected) {
        messageQueue.push(destination)
        return
    }

    stompClient.send(destination, {}, JSON.stringify(body))
}

export const createSimulation = (config) => {
    sendMessage('/app/create', config);
};

export const startSimulation = () => {
    sendMessage('/app/start', {});
};

export const pauseSimulation = () => {
    sendMessage('/app/pause', {});
};

export const resumeSimulation = () => {
    sendMessage('/app/resume', {});
};

export const setSimulationSpeed = (speed) => {
    sendMessage('/app/setSpeed', speed);
};