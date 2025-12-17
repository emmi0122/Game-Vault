import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { getProfile } from '../endpoints/ProfileEndpoints';
import type { Profile } from "../interfaces/UserTypes.ts";
import style from '../style/Profile.module.css'
import Button from "./ButtonComponent.tsx";

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
        <div className={style.profileContainer}>
            {profile ? (
                <>
                    <img
                        src={profile.avatarURL}
                        alt="Avatar"
                        className={style.avatar}
                    />
                    <h2>{profile.profileName}</h2>
                    <p className={style.realNameText}>{profile.realName}</p>
                </>
            ) : (
                <>
                    <h2>👋 Welcome!</h2>
                    <p>
                        You are not logged in.
                    </p>
                    <Button to={'/login'} title={'Log in'} />
                </>
            )}
        </div>
    );
}