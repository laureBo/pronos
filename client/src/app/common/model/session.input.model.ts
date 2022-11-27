import { MatchInput } from './match.input.model';

export interface SessionLightInput {
  id: number;
  nomSession: string;
  dateCreationSession: Date;
  pseudoCreateur: string;
}

export interface SessionInput extends SessionLightInput {
  matchs: MatchInput[];
  participants: string[];
}
