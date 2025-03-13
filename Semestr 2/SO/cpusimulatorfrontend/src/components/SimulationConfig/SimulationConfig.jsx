import "./SimulationConfig.css"
//import sendConfig from "src/services/configServices.js"
import {createSimulation, connectWebSocket, startSimulation} from "./../../services/webSocketServices"
import {useEffect, useState} from "react";

function SimulationConfig() {
    const [config, setConfig] = useState({
        processesRandomGenerated: true,
        numberOfProcesses: 1000,
        minProcessTime: 1,
        maxProcessTime: 10,
        rrTimeQuantum: 5,
        update: true
    });

    useEffect(() => {
        const disconnect = connectWebSocket(
            () => console.log("Connected websocket"),
            (algorithm, state) => console.log(`Received update for ${algorithm}:`, state)
        )

        return disconnect;
    }, [])

    const handleModeChange = (e) => {
        let randomGenerated;
        if (e.target.id === "random") {
            randomGenerated = true;
        } else {
            randomGenerated = false;
        }

        setConfig({
            ...config,
            mode: randomGenerated
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
        setTimeout(() => {
            startSimulation();
        }, 500);
    }

    return (
        <form onSubmit={handleSubmit}>
            <h1>Konfiguracja symulacji</h1>
            <fieldset>
                <div>
                    <input type="radio" id="random" name="mode" onChange={handleModeChange} checked={config.mode==="random"} />
                    <label htmlFor="random">Randomly generated processes</label>
                </div>
                <div>
                    <input type="radio" id="starve" name="mode" onChange={handleModeChange} checked={config.mode==="starve"} />
                    <label htmlFor="starve">Process starving presentation</label>
                </div>
            </fieldset>
            {config.processesRandomGenerated === true && (
                <fieldset>
                    <label htmlFor="numberOfProcesses">Number of processes</label>
                    <input type="number" id="numberOfProcesses" name="numberOfProcesses" defaultValue={1000} onChange={handleNumericChange} />
                    <label htmlFor="minProcessTime">Minimal process execution time</label>
                    <input type="number" id="minProcessTime" name="minProcessTime" defaultValue={1} onChange={handleNumericChange} />
                    <label htmlFor="maxProcessTime">Maximal process execution time</label>
                    <input type="number" id="maxProcessTime" name="maxProcessTime" defaultValue={10} onChange={handleNumericChange} />
                </fieldset>
            )}
            <fieldset>
                <label htmlFor="rrTimeQuantum">Round robin time quantum</label>
                <input type="number" id="rrTimeQuantum" name="rrTimeQuantum" defaultValue={5} onChange={handleNumericChange} />
                <input type="radio" id="sendUpdates" name="update" onChange={handleUpdateChange} defaultChecked/>
                <label htmlFor="random">Visualise simulation</label>
                <input type="radio" id="dontSendUpdates" name="update" onChange={handleUpdateChange} />
                <label htmlFor="random">Show only results</label>
            </fieldset>
            <button type="submit">Start simulation</button>
        </form>
    );
}

export default SimulationConfig;