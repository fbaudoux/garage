import {Teammate} from "./Teammate";

export interface Team {
  id: number;
  name: string;
  teammates : Teammate[];
}
