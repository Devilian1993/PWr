import {useEffect, useState} from 'react'
import './App.css'
import SimulationConfig from "./components/SimulationConfig/SimulationConfig.jsx";
import Simulation from "./components/Simulation/Simulation.jsx";
import {connectWebSocket} from "./services/webSocketServices.js";
import { WebSocketProvider, useWebSocket } from './services/webSocketContext.jsx';

function AppContent() {
  const [isSimulationStarted, setSimulationStarted] = useState(false);
  const { updateSimulationData } = useWebSocket();

  const onSimulationStart = () => {
      setSimulationStarted(true);
  }

  useEffect(() => {
        const disconnect = connectWebSocket(
            () => console.log("Connected websocket"),
            (data) => updateSimulationData(data),
        )

        return disconnect;
    }, [updateSimulationData]);

    return (
        <>
            {!isSimulationStarted ? (
                <SimulationConfig onSimulationStart={onSimulationStart} />
            ) : (
                <Simulation />
            )}
        </>
    )
}

function App() {
    return (
        <WebSocketProvider>
            <AppContent />
        </WebSocketProvider>
    );
}

export default App;
