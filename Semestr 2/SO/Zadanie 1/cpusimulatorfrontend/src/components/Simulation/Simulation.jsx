//import { useState } from 'react'
import "./Simulation.css"
import { useWebSocket } from "../../services/webSocketContext.jsx"

function Simulation() {
    const { simulationData, isSimulationComplete } = useWebSocket()

    const refreshPage = () => {
        window.location.reload()
    }

    return (
        <>
            {!isSimulationComplete ? (
                <h1>Symulacja trwa.</h1>
            ) : (
                <>
                    <h1>Wyniki symulacji:</h1>
                    {simulationData.map((result) => (
                        <div  className="result">
                            <h3>Algorytm: {result.name}</h3>
                            <p>Średni czas oczekiwania: {result.avgWaitingTime}</p>
                            <p>Maksymalny czas oczekiwania: {result.maximumWaitingTime}</p>
                            <p>Liczba zmian kontekstu: {result.contextChanges}</p>
                            <p>Liczba procesów zagłodzonych: {result.numberOfStarvedProcesses}</p>
                        </div>
                    ))}
                    <button type="button" onClick={refreshPage} >Nowa symulacja</button>
                </>
            )}
        </>
    )
}

export default Simulation;