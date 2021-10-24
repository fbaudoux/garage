import {Teammate} from "../teams/Teammate";
import {Skill} from "../teams/Skill";
import {CrewMember} from "./CrewMember";

export interface Crew {
  name: string;
  members : CrewMember[];
}
