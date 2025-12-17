import type { Game } from "../interfaces/GameTypes"

const baseUrl = "http://localhost:8082"
const mapping = "/api/games"

export async function getAllGames() :Promise<Game[]> {
    try {
        const response = await fetch(`${baseUrl}${mapping}`)

        if (!response.ok) {
            throw new Error(`HTTP error ${response.status}`)
        }

        const listOfGames: Game[] = await response.json()
        return listOfGames
    } catch (error) {
        return [] as Game[]
    }
}

export async function getGamesById(gameId: string): Promise<Game | undefined> {
    try {
        const response = await fetch(`${baseUrl}${mapping}/${gameId}`, {
            method: "GET",
            headers: { "Content-Type": "application/json" }
        })

        if (!response.ok) {
            throw new Error(`HTTP error ${response.status}`)
        }

        const game: Game = await response.json()
        return game
    } catch (error) {
        return undefined
    }
}