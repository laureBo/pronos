export interface PariDetail {
  idMatch: number;
  equipe1: string;
  pariEquipe1: number | null;
  equipe2: string;
  pariEquipe2: number | null;
  scoreEquipe1: number | null;
  scoreEquipe2: number | null;
  dateMatch: Date;
  isWinner: boolean | null;
  isPerfect?: boolean | null;
}
