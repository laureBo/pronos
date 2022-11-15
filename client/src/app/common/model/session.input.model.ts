import { MatchInput } from './match.input.model';

export interface SessionInput {
  id: number;
  nomSession: string;
  dateCreationSession: Date;
  pseudoCreateur: string;
  matchs: MatchInput[];
  participants: string[];
}
