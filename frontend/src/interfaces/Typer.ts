export interface User {
  email: string;
  password: string;
}

export interface Profile {
  profileName: string;
  realName: string;
  avatarURL: string;
  roles: string[];
}

export interface RegistrationRequestDTO {
  user: User;
  profile: Profile;
}