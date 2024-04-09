export interface ILogin {
  username: string;
  password: string;
}

export interface IRegister extends ILogin {
  role: string;
}

export interface IToken {
  token: string;
}
