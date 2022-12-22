import { MatchOutput } from './match.output.model';

export interface SessionLightInput {
  id: number;
  nomSession: string;
  dateCreationSession: Date;
  pseudoCreateur: string;
}

export interface SessionInput extends SessionLightInput {
  matchs: MatchOutput[];
  participants: string[];
}
