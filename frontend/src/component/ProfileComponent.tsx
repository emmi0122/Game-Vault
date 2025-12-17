import { useNavigate } from 'react-router-dom';
import './../style/Home.css'
import { useEffect, useState } from 'react';
import { getProfile } from '../endpoints/ProfileEndpoints';
import type { Profile } from "../interfaces/UserTypes.ts";

export default function ProfileComponent() {
    const [profile, setProfile] = useState<Profile | undefined>(undefined);

    const navigate = useNavigate();

    //Remov profile EVERYTTIME we go to home page
    useEffect(() => {
        const stordeUserId = localStorage.getItem("profileId");
        if (!stordeUserId) {
            return
        }
        getProfile(stordeUserId).then(profile => setProfile(profile));

    }, []);

    async function consoleProfile() {
        const storedUserId = localStorage.getItem("profileId");
        if (storedUserId) {
            const profile = await getProfile(storedUserId);
            if (profile) {
                console.log("Hej:", profile);
                setProfile(profile)
            } else {
                console.log("Error fetching profile:");
            }
        } else {
            console.log("no profile")
        }
    }

    return (
        <div className="profile-section">
            {profile ? (
                <>
                    <img
                        src={profile.avatarURL}
                        alt="Avatar"
                        className="avatar"
                    />
                    <h2>{profile.profileName}</h2>
                    <p className="real-name">{profile.realName}</p>
                </>
            ) : (
                <>
                    <h2>👋 Welcome!</h2>
                    <p className="real-name">
                        You are not logged in.
                    </p>

                    <button
                        className="primary-btn"
                        onClick={() => navigate("/login")}
                    >
                        Login
                    </button>
                </>
            )}
        </div>
    );
}