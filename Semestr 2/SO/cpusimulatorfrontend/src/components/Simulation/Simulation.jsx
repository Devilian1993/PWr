//import { useState } from 'react'
import { useWebSocket } from "../../services/webSocketContext.jsx"

function Simulation() {
    const { simulationData, isSimulationComplete } = useWebSocket()

    return (
        <>
            {!isSimulationComplete ? (
                <h1>Simulation is running.</h1>
            ) : (
                <>
                    <h1>Simulation results:</h1>
                    {simulationData.map((result) => (
                        <div class="result" className="simulation-result">
                            <h3>Algorytm: {result.name}</h3>
                            <p>Średni czas oczekiwania: {result.avgWaitingTime}</p>
                            <p>Maksymalny czas oczekiwania: {result.maximumWaitingTime}</p>
                            <p>Liczba zmian kontekstu: {result.contextChanges}</p>
                        </div>
                    ))}
                </>
            )}
        </>
    )
}

export default Simulation;