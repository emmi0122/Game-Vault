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

export interface LoginResponse {
  status: string;
  message: string;
  profileId?: number;  // optional if fail
}

export interface CsrfResponse {
  token: string;
  headerName: string;
}