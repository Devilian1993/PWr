import {createContext, useCallback, useContext, useEffect, useState} from "react"

const WebSocketContext = createContext(null)

export const useWebSocket = () => useContext(WebSocketContext)

export const WebSocketProvider = ({ children }) => {
    const [ simulationData, setSimulationData ] = useState([])

    useEffect(() => {
        console.log("Zaktualizowane simulationData:", simulationData);
    }, [simulationData]);


    const updateSimulationData = useCallback((result) => {
        setSimulationData(prevResults => [...prevResults, result]);
    }, []);
    
    const isSimulationComplete = simulationData.length === 3;

    return (
        <WebSocketContext.Provider value={{
            simulationData,
            updateSimulationData,
            isSimulationComplete
        }}>
            {children}
        </WebSocketContext.Provider>
    );
}
