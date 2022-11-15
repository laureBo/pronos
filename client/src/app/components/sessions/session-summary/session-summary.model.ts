export enum SessionStatusEnum {
  COMMING = 'A venir',
  INPROGRESS = 'En cours',
  FINISHED = 'Terminé',
}

export interface SessionSummary {
  title: string;
  id: number;
  date: Date;
  creator: string;
  status: SessionStatusEnum;
}
