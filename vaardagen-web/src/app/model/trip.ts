export interface Trip {
  id: string | undefined;
  departureDate: string;
  departurePort: string;
  arrivalDate: string;
  arrivalPort: string;
  daysAtSea: number | undefined;
}
