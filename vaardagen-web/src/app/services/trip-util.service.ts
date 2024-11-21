import dayjs from "dayjs";

export function calculateDaysAtSea(departureDate: Date, arrivalDate: Date): number {
  const diff = dayjs(arrivalDate).diff(departureDate, 'day');
  return diff + 1;
}

export function createTripId(departureDate: Date) {
  const startDate = dayjs(departureDate).add(1, 'day').toDate();
  return startDate.getFullYear().toString().substring(2) +
    (startDate.getMonth() + 1).toString().padStart(2, '0') +
    startDate.getDate().toString().padStart(2, '0')
}
