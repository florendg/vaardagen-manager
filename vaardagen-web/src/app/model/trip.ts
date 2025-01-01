export interface Trip {
  uuid: string | undefined,
  tripNumber: string | undefined;
  departureDate: string;
  departurePort: string;
  arrivalDate: string;
  arrivalPort: string;
  daysAtSea: number | undefined;
}
