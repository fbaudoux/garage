import {Skill} from "./Skill";

export interface Teammate {
  id: number;
  name: string;
  skills : Skill[];
}
