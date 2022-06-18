export interface IRegion {
  id?: number;
  regionName?: string | null;
}

export class Region implements IRegion {
  constructor(public id?: number, public regionName?: string | null) {}
}

export function getRegionIdentifier(region: IRegion): number | undefined {
  return region.id;
}
