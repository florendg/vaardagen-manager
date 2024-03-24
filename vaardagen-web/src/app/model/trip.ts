export interface Trip {
  id: string | undefined;
  departureDate: Date;
  departurePort: string;
  arrivalDate: Date;
  arrivalPort: string;
  daysAtSea: number | undefined;
}
