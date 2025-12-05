export interface User {
  email: string;
  realName: string;
  password: string;
}

export interface Profile {
  profileName: string;
  avatarURL: string;
  roles: string[];
}

export interface RegistrationRequestDTO {
  user: User;
  profile: Profile;
}