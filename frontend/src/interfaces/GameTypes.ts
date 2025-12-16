export interface Game {
    id: number
    title: string
    genre: string
    pegi: number
    releaseDate: number
    developedBy: number

    platforms: Platform[]
}

export interface Platform {
    id: number
    name: string
}