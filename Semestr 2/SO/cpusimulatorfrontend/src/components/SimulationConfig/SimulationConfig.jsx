import "./SimulationConfig.css"
//import sendConfig from "src/services/configServices.js"
import {createSimulation, connectWebSocket, startSimulation} from "./../../services/webSocketServices"
import {useEffect, useState} from "react";

function SimulationConfig({ onSimulationStart }) {
    const [config, setConfig] = useState({
        mode: "random",
        numberOfProcesses: 1000,
        minProcessTime: 1,
        maxProcessTime: 10,
        rrTimeQuantum: 5,
        update: true,
        ticksPerNewProcess: 5
    });

    const handleModeChange = (e) => {
        setConfig({
            ...config,
            mode: e.target.id
        });
    }

    const handleNumericChange = (e) => {
        setConfig({
            ...config,
            [e.target.id]: parseInt(e.target.value) || 0
        })
    }

    const handleUpdateChange = (e) => {
        let sendUpdates = e.target.id === "sendUpdates";

        setConfig({
            ...config,
            update: sendUpdates
        })
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(config);
        createSimulation(config);

        if(onSimulationStart) {
            onSimulationStart();
        }

        setTimeout(() => {
            startSimulation();
        }, 500);
    }

    return (
        <form id="config" onSubmit={handleSubmit}>
            <h1>Konfiguracja symulacji</h1>
            <fieldset>
                <div>
                    <input type="radio" id="random" name="mode" onChange={handleModeChange} checked={config.mode==="random"} />
                    <label htmlFor="random">Generuj procesy losowo</label>
                </div>
                <div>
                    <input type="radio" id="starve" name="mode" onChange={handleModeChange} checked={config.mode==="starve"} />
                    <label htmlFor="starve">Pobierz procesy z pliku</label>
                </div>
                <div>
                <input type="radio" id="bursts" name="mode" onChange={handleModeChange} checked={config.mode==="bursts"} />
                <label htmlFor="starve">Generuj "wybuchy" procesów</label>
                </div>
            </fieldset>
            {config.mode !== "starve" && (
                <fieldset>
                    <label htmlFor="numberOfProcesses">Liczba procesów</label>
                    <input type="number" id="numberOfProcesses" name="numberOfProcesses" defaultValue={1000} onChange={handleNumericChange} />
                    <label htmlFor="minProcessTime">Minimalny czas wykonywania procesu</label>
                    <input type="number" id="minProcessTime" name="minProcessTime" defaultValue={1} onChange={handleNumericChange} />
                    <label htmlFor="maxProcessTime">Maksymalny czas wykonywania procesu</label>
                    <input type="number" id="maxProcessTime" name="maxProcessTime" defaultValue={10} onChange={handleNumericChange} />
                </fieldset>
            )}
            <fieldset>
                <label htmlFor="rrTimeQuantum">Kwant czasu Round Robin</label>
                <input type="number" id="rrTimeQuantum" name="rrTimeQuantum" defaultValue={5} onChange={handleNumericChange} />
                <label htmlFor="ticksPerNewProcesses">Okres generowania nowych procesów</label>
                <input type="number" id="ticksPerNewProcess" name="ticksPerNewProces" defaultValue={5} onChange={handleNumericChange} />
            </fieldset>
            <button type="submit">Rozpocznij symulację</button>
        </form>
    );
}

export default SimulationConfig;