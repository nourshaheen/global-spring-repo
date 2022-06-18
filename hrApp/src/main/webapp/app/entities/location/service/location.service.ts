import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ILocation, getLocationIdentifier } from '../location.model';

export type EntityResponseType = HttpResponse<ILocation>;
export type EntityArrayResponseType = HttpResponse<ILocation[]>;

@Injectable({ providedIn: 'root' })
export class LocationService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/locations');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(location: ILocation): Observable<EntityResponseType> {
    return this.http.post<ILocation>(this.resourceUrl, location, { observe: 'response' });
  }

  update(location: ILocation): Observable<EntityResponseType> {
    return this.http.put<ILocation>(`${this.resourceUrl}/${getLocationIdentifier(location) as number}`, location, { observe: 'response' });
  }

  partialUpdate(location: ILocation): Observable<EntityResponseType> {
    return this.http.patch<ILocation>(`${this.resourceUrl}/${getLocationIdentifier(location) as number}`, location, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILocation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILocation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addLocationToCollectionIfMissing(locationCollection: ILocation[], ...locationsToCheck: (ILocation | null | undefined)[]): ILocation[] {
    const locations: ILocation[] = locationsToCheck.filter(isPresent);
    if (locations.length > 0) {
      const locationCollectionIdentifiers = locationCollection.map(locationItem => getLocationIdentifier(locationItem)!);
      const locationsToAdd = locations.filter(locationItem => {
        const locationIdentifier = getLocationIdentifier(locationItem);
        if (locationIdentifier == null || locationCollectionIdentifiers.includes(locationIdentifier)) {
          return false;
        }
        locationCollectionIdentifiers.push(locationIdentifier);
        return true;
      });
      return [...locationsToAdd, ...locationCollection];
    }
    return locationCollection;
  }
}
